package cn.demo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 删除带有权限信息的节点示例
 */
public class zkAuthDelete {
    final static String PATH = "/zk-test-auth_test";
    final static String PATH2 = "/zk-test-auth_test/child";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //创建带有权限信息的节点
        ZooKeeper zooKeeper1 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper1.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper1.create(PATH,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zooKeeper1.create(PATH2,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.EPHEMERAL);

        //没有授权的Session删除子节点
        try {
            ZooKeeper zooKeeper2 = new ZooKeeper("192.168.159.128:2181",5000,null);
            zooKeeper2.delete(PATH2,-1);
        } catch (Exception e) {
            System.out.println("删除子节点失败："+e.getMessage());
        }

        //有授权的Session删除子节点
        ZooKeeper zooKeeper3 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper3.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper3.delete(PATH2,-1);
        System.out.println("成功子删除节点："+PATH2);

        //没有授权的Session删除父节点
        ZooKeeper zooKeeper4 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper4.delete(PATH,-1);
        System.out.println("成功父删除节点："+ PATH);
    }
}
