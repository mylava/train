package cn.mylava.mapreduce.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计文本中每个单词出现的次数。
 * Created by lipengfei on 2017/5/8.
 */
public class CombinerWC {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //组装MapReduce任务

        //对Job进行个性化配置，如果不进行配置，则读取全局的配置(mapred-site.xml)
        Configuration conf = new Configuration();
        //1.构造job对象用来组装MR
        Job job = Job.getInstance(conf);

        //2.设置job所在jar的入口
        job.setJarByClass(CombinerWC.class);

        //3.设置Mapper相关的属性
        job.setMapperClass(WCMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileInputFormat.setInputPaths(job,new Path("/word"));

        //4.设置Reducer相关的属性
        job.setReducerClass(WCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileOutputFormat.setOutputPath(job,new Path("/mywc"));

        //5.设置Combiner,Combiner是一种特殊的Reducer
        job.setCombinerClass(WCReducer.class);

        //6.提交任务
        job.waitForCompletion(true);

    }
}
