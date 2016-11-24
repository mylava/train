package cn.mylava.hadoop.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by mylava on 2016/11/17.
 *
 * Mapper需要继承Mapper类
 * K1 为字符所在位置  V1为一行文本   K2为单个单词  V2为单词出现次数
 */
public class WCMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.接收数据
        String line = value.toString();
        //2.切分数据
        String[] words = line.split(" ");
        //3.处理数据
        for (String w : words) {
            //出现一次，记一个1,写出（序列化）
            context.write(new Text(w.trim()),new LongWritable(1));
        }
    }
}
