package cn.demo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * ZooKeeper权限控制示例
 */
public class zkAuth {
    final static String PATH = "/zk-test-auth_test";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //此处的Watcher为null
        ZooKeeper zooKeeper1 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper1.addAuthInfo("digest","foo:true".getBytes());
        //创建的节点也带有权限信息
        zooKeeper1.create(PATH,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        //读写节点信息都需要权限---此处为权限信息正确的情况
        ZooKeeper zooKeeper2 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper2.addAuthInfo("digest","foo:true".getBytes());
        System.out.println("==============="+zooKeeper2.getData(PATH,false,null));

        //---此处为权限信息错误的情况
        ZooKeeper zooKeeper3 = new ZooKeeper("192.168.159.128:2181",5000,null);
        zooKeeper3.addAuthInfo("digest","foo:123".getBytes());
        zooKeeper3.getData(PATH,false,null);

        Thread.sleep(Integer.MAX_VALUE);
    }


}
