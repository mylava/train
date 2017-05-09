package cn.mylava.mapreduce.partitioner;

import cn.mylava.mapreduce.datacount.DataBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 将数据按照规则进行分区，如果没有设置分区规则，采用hadoop默认的分区规则
 * Created by lipengfei on 2017/5/9.
 */
public class PartitionerData {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //对Job进行个性化配置，如果不进行配置，则读取全局的配置(mapred-site.xml)
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(PartitionerData.class);
        job.setMapperClass(DCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(cn.mylava.mapreduce.datacount.DataBean.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        job.setReducerClass(DCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(cn.mylava.mapreduce.datacount.DataBean.class);
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //将分区处理对象组合到Job中
        job.setPartitionerClass(ProviderPartitioner.class);
        /**
         * 设置reducer的个数
         * hadoop默认启动一个Reducer，如果只有一个Reducer，所有结果都会输出到一个文件中
         * 根据这里的业务需求，应该将reducer的个数设置为4个，
         * 通过参数形式可以查看分别输入1、2、3、4、5的效果
         */
        job.setNumReduceTasks(Integer.parseInt(args[2]));

        job.waitForCompletion(true);
    }

    /**
     * 根据运营商对数据进行分区
     * Partitioner在Mapper执行完成之后,Reducer执行之前执行
     * 故Partitioner的入参是Mapper的输出参数，这里需要确定的两个泛型是Partitioner输出的参数类型
     */
    static class ProviderPartitioner extends Partitioner<Text, DataBean> {
        //声明一个map用来标识运营商
        private static Map<String,Integer> providerMap = new HashMap<>();
        //静态代码块初始化运营商
        static {
            providerMap.put("135",1);
            providerMap.put("136",1);
            providerMap.put("139",1);
            providerMap.put("150",2);
            providerMap.put("159",2);
            providerMap.put("182",3);
            providerMap.put("183",3);
        }
        @Override
        public int getPartition(Text text, DataBean dataBean, int numPartitions) {
            String telNo = text.toString();
            //获取手机号码前三位，根据手机号码前三位判断运营商
            String sub_telNo = telNo.substring(0,3);
            return providerMap.get(sub_telNo) == null ? 0 : providerMap.get(sub_telNo);
        }
    }

    static class DCMapper extends Mapper<LongWritable,Text,Text, cn.mylava.mapreduce.datacount.DataBean> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //接收数据
            String line = value.toString();
            //分割数据
            String[] fields = line.split("\t");
            //取出需要的数据
            String telNo = fields[1];
            long up = Long.parseLong(fields[8]);
            long down = Long.parseLong(fields[9]);

            cn.mylava.mapreduce.datacount.DataBean bean = new cn.mylava.mapreduce.datacount.DataBean(telNo,up,down);
            //输出键值对
            context.write(new Text(telNo),bean);
        }
    }

    static class DCReducer extends Reducer<Text, cn.mylava.mapreduce.datacount.DataBean,Text, cn.mylava.mapreduce.datacount.DataBean> {
        @Override
        protected void reduce(Text key, Iterable<cn.mylava.mapreduce.datacount.DataBean> values, Context context) throws IOException, InterruptedException {
            long up_sum = 0;
            long down_sum = 0;

            for (cn.mylava.mapreduce.datacount.DataBean bean:values) {
                up_sum += bean.getUpPayLoad();
                down_sum += bean.getDownPayLoad();
            }

            cn.mylava.mapreduce.datacount.DataBean bean = new DataBean("",up_sum,down_sum);

            context.write(key,bean);
        }
    }
}
