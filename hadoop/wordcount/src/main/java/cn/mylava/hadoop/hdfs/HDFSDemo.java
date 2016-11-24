package cn.mylava.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by mylava on 2016/11/16.
 *
 * Java调用hdfs接口上传、下载文件等操作
 *
 */
public class HDFSDemo {

    FileSystem fs = null;
    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        /**
         * windows系统，修改C:\Windows\System32\drivers\etc\hosts文件
         * linux系统，修改/etc/hosts文件
         * 添加添加192.168.159.128 mylava0001映射
         * 如果不使用域名的话，直接写IP即可
         */
        fs = FileSystem.get(new URI("hdfs://192.168.159.128:9000"), new Configuration(),"root");
    }

    /**
     * 测试上传
     * @throws IOException
     */
    @Test
    public void testUpload() throws IOException {
        InputStream is = new FileInputStream("d:/test.txt");
        OutputStream os = fs.create(new Path("/test.txt"));
        IOUtils.copyBytes(is, os, 4096, true);

    }

    @Test
    public void testDownload() throws IOException {
        InputStream is = fs.open(new Path("/hadoop"));
        OutputStream os = new FileOutputStream("d:/hadoop_copy");
        IOUtils.copyBytes(is,os,4096,true);

//        fs.copyToLocalFile(new Path("/hadoop"),new Path("d:/hadoop_copy"));

    }
    @Test
    public void testDel() throws IOException {
        //第二个参数表示是否遍历
        boolean flag =  fs.delete(new Path("/test.txt"),false);
        System.out.println(flag);
    }

    @Test
    public void testMkdir() throws IOException {
        boolean flag = fs.mkdirs(new Path("/testdir"));
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        FileSystem fs = FileSystem.get(new URI("hdfs://mylava0001:9000"), new Configuration());
        FileStatus status = fs.getFileStatus(new Path("/hadoop"));
        System.out.println(status);

        InputStream is = fs.open(new Path("/hadoop"));

        OutputStream os = new FileOutputStream("d:/hadoop_copy");

        IOUtils.copyBytes(is,os,4096,true);

    }
}
