package cn.mylava._001;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author lipengfei
 */
public class HbaseDemo {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","hadoop1:2181,hadoop2:2181,hadoop3:2181");

        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();



        conn.close();


    }
}
