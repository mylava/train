package cn.mylava.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by mylava on 2016/6/28.
 */
public class CreateNode {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.159.130:2181",5000);
        String path = "/zk-book/c1";
        zkClient.createPersistent(path,true);
    }
}
