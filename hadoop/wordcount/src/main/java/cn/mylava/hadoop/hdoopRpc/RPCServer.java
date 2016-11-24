package cn.mylava.hadoop.hdoopRpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by mylava on 2016/11/17.
 *
 * 使用Hadoop框架实现RPC服务端
 */
public class RPCServer implements Bizable{
    public String sayHi(String name) {
        return "Hi , " + name;
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration() ;
        //注册服务
        RPC.Server server = new RPC.Builder(conf).setProtocol(Bizable.class)
                .setInstance(new RPCServer()).setBindAddress("192.168.159.1").setPort(9527).build();
        //启动服务
        server.start();
        System.out.println("已启动！");
    }
}
