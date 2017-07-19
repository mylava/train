package cn.mylava.mapreduce.inverseindex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by lipengfei on 2017/5/10.
 */
public class InverseIndex {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(InverseIndex.class);

        job.setMapperClass(IndexMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setCombinerClass(IndexCombiner.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        job.setReducerClass(IndexReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }

    /**
     * Mapper
     */
    public static class IndexMapper extends Mapper<LongWritable,Text,Text,Text> {
        private Text k = new Text();
        private Text v = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //接收数据
            String line = value.toString();
            //分割数据
            String[] words = line.split(" ");
            //通过上下文环境获取文件路径
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            //通过文件路径获得文件名
            String fileName = fileSplit.getPath().toString();

            for (String word : words) {
                k.set(word +"@" + fileName);
                v.set("1");
                context.write(k,v);
            }
        }
    }

    /**
     * Combiner
     */
    public static class IndexCombiner extends Reducer<Text,Text,Text,Text> {
        private Text k = new Text();
        private Text v = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String[] wordAndPath = key.toString().split("@");
            String word = wordAndPath[0];
            String path = wordAndPath[1];
            int counter = 0;
            for (Text t:values) {
                counter += Integer.parseInt(t.toString());
            }
            k.set(word);
            v.set(path.replace("hdfs://hadoop:9000/inverse/","")+"->"+counter);
            context.write(k,v);
        }
    }

    /**
     * Reducer
     */
    public static class IndexReducer extends Reducer<Text,Text,Text,Text> {
        private Text v = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuilder result = new StringBuilder();
            for (Text t : values) {
                result.append(t.toString()).append("\t");
            }
            v.set(result.toString());
            context.write(key,v);
        }
    }

}
