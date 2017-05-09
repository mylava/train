package cn.mylava.mapreduce.combiner;

/**
 * Created by lipengfei on 2017/5/8.
 */

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WCReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        //接收数据

        //定义计数器
        long counter = 0;
        for (LongWritable i : values) {
            counter+= i.get();
        }
        //输出
        context.write(key,new LongWritable(counter));
    }
}