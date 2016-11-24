package cn.mylava.hadoop.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by mylava on 2016/11/17.
 *
 * 统计文件中每个单词出现的次数
 *
 */
public class WordCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取任务实例，需要传入一个Configuration对象
        Job job = Job.getInstance(new Configuration());
        //1.设置Jar包Main函数所在的类
        job.setJarByClass(WordCount.class);
        //2.设置mapper属性
        job.setMapperClass(WCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        FileInputFormat.setInputPaths(job,new Path("/wrods.dat"));
        //3.设置reducer属性
        job.setReducerClass(WCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileOutputFormat.setOutputPath(job,new Path("/wcout1118"));

        //4.设置combiner进行本地合计
        //可插拔的combiner只是提高效率（分布计算、减少网络传输），并不影响结算结果
        //不可插拔的combiner也有，比如过滤无效数据
        job.setCombinerClass(WCReducer.class);

        //4.提交任务
        job.waitForCompletion(true);
    }
}
