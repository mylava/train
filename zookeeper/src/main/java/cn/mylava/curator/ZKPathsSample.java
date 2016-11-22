package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by mylava on 2016/7/5.
 */
public class ZKPathsSample {
    static String path = "/curator_zkpath";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.129:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .sessionTimeoutMs(5000)
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();

        System.out.println(ZKPaths.fixForNamespace("sub",path));
        System.out.println(ZKPaths.makePath(path,"sub"));
        System.out.println(ZKPaths.getNodeFromPath("/curator_zkpath/sub1"));

        ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode("/curator_zkpath/sub1");
        System.out.println(pn.getPath());
        System.out.println(pn.getNode());

        String dir1 = path + "/child1";
        String dir2 = path + "/child2";
        ZKPaths.mkdirs(zooKeeper,dir1);
        ZKPaths.mkdirs(zooKeeper,dir2);
        System.out.println(ZKPaths.getSortedChildren(zooKeeper,path));

        ZKPaths.deleteChildren(client.getZookeeperClient().getZooKeeper(),path,true);

    }
}
