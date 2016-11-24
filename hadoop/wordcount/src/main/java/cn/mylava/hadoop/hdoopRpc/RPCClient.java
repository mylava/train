package cn.mylava.hadoop.hdoopRpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by mylava on 2016/11/17.
 *
 * RPC客户端调用服务端
 */
public class RPCClient {

    public static void main(String[] args) throws IOException {
        //获取RPC代理对象
        Bizable proxy = RPC.getProxy(Bizable.class, 10, new InetSocketAddress("192.168.159.1", 9527), new Configuration());
        //调用代理方法
        String result = proxy.sayHi("Tommy");
        System.out.println(result);

    }
}