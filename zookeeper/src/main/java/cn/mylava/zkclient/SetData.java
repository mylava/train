package cn.mylava.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by mylava on 2016/6/28.
 */
public class SetData {
    public static void main(String[] args) throws InterruptedException {
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient("192.168.159.130:2181",5000);
        zkClient.createEphemeral(path,new Integer(1));
        zkClient.writeData(path,new Integer(2));
        Thread.sleep(10000);
        zkClient.delete(path);
    }
}
