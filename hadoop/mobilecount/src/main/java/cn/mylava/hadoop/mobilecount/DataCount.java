package cn.mylava.hadoop.mobilecount;

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
 * Created by mylava on 2016/11/18.
 * 1.统计单个手机号上行流量、下行流量和总流量
 * 2.将不同运营商的手机号放到不同的分区中
 */
public class DataCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.获取Job实例
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //2.设置Jar包的入口
        job.setJarByClass(DataCount.class);

        //3.设置Mapper属性
        job.setMapperClass(DCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DataBean.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        //4.设置Reducer属性
        job.setReducerClass(DCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DataBean.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //5.设置分区类，shuffle将依据分区类的规则进行分区
        job.setPartitionerClass(ProviderPartitioner.class);

        //6.设置reducer个数
        job.setNumReduceTasks(Integer.parseInt(args[2]));
        //7.提交任务
        job.waitForCompletion(true);

    }

    /**
     * Mapper类
     */
    public static class DCMapper extends Mapper<LongWritable,Text,Text,DataBean> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //接收数据
            String line = value.toString();
            String[] fields = line.split("\t");
            String telNo = fields[1];
            long up = Long.parseLong(fields[8]);
            long down = Long.parseLong(fields[9]);
            //new出来的对象会占用大量内存
            DataBean bean = new DataBean(telNo,up,down);
            context.write(new Text(telNo),bean);
        }
    }

    /**
     * Reducer类
     */
    public static class DCReducer extends Reducer<Text,DataBean,Text,DataBean>{
        @Override
        protected void reduce(Text key, Iterable<DataBean> values, Context context) throws IOException, InterruptedException {
            long upSum = 0;
            long downSum = 0;

            for (DataBean bean : values) {
                upSum += bean.getUpPayLoad();
                downSum += bean.getDownPayLoad();
            }
            //new出来的对象占用大量内存
            DataBean bean = new DataBean("",upSum,downSum);

            context.write(key,bean);
        }
    }

    /**
     * Partitioner类，在mapper后执行
     */
    public static class ProviderPartitioner extends Partitioner<Text,DataBean> {
        private static Map<String,Integer> providerMap = new HashMap<String,Integer>();

        /**
         * 使用静态块，根据号段区分不同运营商----------定义分区规则
         */
        static {
            //运营商1
            providerMap.put("135",1);
            providerMap.put("136",1);
            providerMap.put("137",1);
            providerMap.put("138",1);
            providerMap.put("139",1);
            //运营商2
            providerMap.put("150",2);
            providerMap.put("159",2);
            //运营商3
            providerMap.put("182",3);
            providerMap.put("183",3);

        }
        /**
         *
         * 具体的进行分区的方法
         * @param text   Key  此处为手机号
         * @param dataBean  Value
         * @param numPartitions  分区个数
         * @return   分区号
         */
        @Override
        public int getPartition(Text text, DataBean dataBean, int numPartitions) {
            String account = text.toString();
            String sub_acc = account.substring(0,3);
            Integer code = providerMap.get(sub_acc);
            if (code == null) {
                code = 0;
            }
            return code;
        }
    }

}
