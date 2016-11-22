package cn.mylava.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 删除带有权限信息的节点示例
 *
 * 删除有权限信息的节点操作，其作用范围是其子节点。
 * 也就是说，当我们对一个数据节点添加权限信息后，依然可以自由的删除这个节点，但是对于这个节点的子节点，就必须使用相应的权限信息才能够删除它。
 */
public class ZKAuthDelete {
    final static String PATH = "/zk-book-auth_test";
    final static String PATH2 = "/zk-book-auth_test/child";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper1 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper1.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper1.create(PATH,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zooKeeper1.create(PATH2,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.EPHEMERAL);

        try {
            ZooKeeper zooKeeper2 = new ZooKeeper("192.168.159.128:2181",5000,null);
            zooKeeper2.delete(PATH2,-1);
        } catch (Exception e) {
            System.out.println("删除节点失败："+e.getMessage());
        }

        ZooKeeper zooKeeper3 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper3.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper3.delete(PATH2,-1);
        System.out.println("成功删除节点："+PATH2);

        ZooKeeper zooKeeper4 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper4.delete(PATH,-1);
        System.out.println("成功删除节点："+ PATH);
    }
}
