package cn.mylava.mapreduce.datacount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计日志中每个手机号使用的流量总数。
 * Created by lipengfei on 2017/5/9.
 */
public class DataCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //对Job进行个性化配置，如果不进行配置，则读取全局的配置(mapred-site.xml)
        Configuration conf = new Configuration();
        //1.构造job对象用来组装MR
        Job job = Job.getInstance(conf);

        job.setJarByClass(DataCount.class);
        job.setMapperClass(DCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DataBean.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        job.setReducerClass(DCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DataBean.class);
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }

    static class DCMapper extends Mapper<LongWritable,Text,Text,DataBean> {
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

            DataBean bean = new DataBean(telNo,up,down);
            //输出键值对
            context.write(new Text(telNo),bean);
        }
    }

    static class DCReducer extends Reducer<Text,DataBean,Text,DataBean> {
        @Override
        protected void reduce(Text key, Iterable<DataBean> values, Context context) throws IOException, InterruptedException {
            long up_sum = 0;
            long down_sum = 0;

            for (DataBean bean:values) {
                up_sum += bean.getUpPayLoad();
                down_sum += bean.getDownPayLoad();
            }

            DataBean bean = new DataBean("",up_sum,down_sum);

            context.write(key,bean);
        }
    }
}
