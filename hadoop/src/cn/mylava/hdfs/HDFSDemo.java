package cn.mylava.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

/**
 * HDFS API 示例
 * Created by lipengfei on 2017/5/8.
 */
public class HDFSDemo {

    FileSystem fs = null;
    Configuration conf = null;

    @Before
    public void init() throws IOException, URISyntaxException, InterruptedException {
        conf = new Configuration();
        //创建hdfs操作工具类
        fs = FileSystem.get(new URI("hdfs://hadoop:9000"),conf,"mylava");
    }

    /**
     * 打印conf参数列表
     */
    @Test
    public void testConf(){
        Iterator<Map.Entry<String, String>> iterator = conf.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+" : "+next.getValue());
        }
    }

    /**
     * 测试递归查询指定目录下所有子文件夹中的文件
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws
     */
    @Test
    public void listFile() throws Exception {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while(listFiles.hasNext()){
            LocatedFileStatus next = listFiles.next();
            System.out.print("name:" + next.getPath().getName() + "\t");
            System.out.print("path:" + next.getPath() + "\t");
            System.out.print("blcSize: " + next.getBlockSize() + "\t");
            System.out.println("owner: " + next.getOwner());
            System.out.println("----------------");
        }
    }

    /**
     * 测试显示一个路径下的内容
     */
    @Test
    public void listStatu() throws Exception{
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for(FileStatus fileStatus:listStatus){
            System.out.print(fileStatus.getPath ().getName()+"\t");
            System.out.println(fileStatus.isFile()?"file":"dir");
        }
    }

    /**
     * 下载文件
     * @throws IOException
     */
    @Test
    public void getFile() throws IOException {
        //读取hdfs上的文件，获取该文件的输入流
        InputStream is = fs.open(new Path("/jdk1.7.tar.gz"));
        //在本地文件系统创建接收文件，返回该文件的输出流
        OutputStream os = new FileOutputStream("/Users/lipengfei/Desktop/jdk1.7");
        //将读取的信息复制到本地文件中
        IOUtils.copyBytes(is,os,4096,true);
    }

    /**
     * 上传文件
     */
    @Test
    public void putFile() throws IOException {
        //读取本地系统文件，获取该文件的输入流
        InputStream is = new FileInputStream("/Users/lipengfei/Desktop/hadoop-2.8.0.tar.gz");
        //在hdfs上创建一个文件，返回该文件的输出流
        OutputStream os = fs.create(new Path("/hadoop-2.8.tar.gz"));
        //输入 -> 输出
        IOUtils.copyBytes(is,os,4096,true);
    }

    /**
     * 下载文件
     * @throws IOException
     */
    @Test
    public void download() throws IOException {
        //从hdfs拷贝到本地文件系统
        fs.copyToLocalFile(new Path("/jdk1.7.tar.gz"),new Path("/Users/lipengfei/Desktop/jdk1.7(2)"));
    }

    /**
     * 上传文件
     * @throws IOException
     */
    @Test
    public void upload() throws IOException {
        //从本地文件系统拷贝到hdfs上
        fs.copyFromLocalFile(new Path("/Users/lipengfei/Desktop/hadoop-2.8.0.tar.gz"),new Path("/hadoop-2.8.tar.gz(2)"));
    }

    /**
     * 在hdfs上创建文件夹
     * @throws IOException
     */
    @Test
    public void mkDir() throws IOException {
        //创建目录
        boolean flag = fs.mkdirs(new Path("/testDir"));
        System.out.println(flag);
    }

    /**
     * 删除文件/文件夹
     * @throws IOException
     */
    @Test
    public void delete() throws IOException {
        //删除文件
        boolean flag = fs.delete(new Path("/hadoop-2.8.tar.gz(2)"), false);
        System.out.println(flag);
    }



}
