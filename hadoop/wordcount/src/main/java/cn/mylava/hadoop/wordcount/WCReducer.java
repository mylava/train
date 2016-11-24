package cn.mylava.hadoop.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by mylava on 2016/11/17.
 *
 * Reducer 需要继承Reducer类
 *
 */
public class WCReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> v2s, Context context) throws IOException, InterruptedException {
        Long counter = 0L;
        //处理数据：循环v2s
        for (LongWritable i : v2s) {
            counter += i.get();
        }
        //输出数据（序列化）
        context.write(key,new LongWritable(counter));
    }

}
