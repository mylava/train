package cn.mylava.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * 删除有子节点的数据节点
 */
public class DeleteRecursive {
    public static void main(String[] args) {
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient("192.168.159.130:2181",5000);
        zkClient.createPersistent(path,"");
        zkClient.createPersistent(path+"/c1","");
        zkClient.deleteRecursive(path);
    }
}
