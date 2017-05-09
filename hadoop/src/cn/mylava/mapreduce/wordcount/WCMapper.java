package cn.mylava.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by lipengfei on 2017/5/8.
 *
 * 参数通过RPC进行传递，需要进行序列化和反序列化操作，
 * hadoop提供的序列化操作接口是Writable。
 */
public //实现Mapper
class WCMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //接收数据
        String line = value.toString();
        //切分
        String[] words = line.split(" ");
        //根据单词生成输出键值对
        for (String w : words) {
            context.write(new Text(w),new LongWritable(1));
        }
    }
}
