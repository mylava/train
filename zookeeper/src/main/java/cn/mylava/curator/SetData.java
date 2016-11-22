package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by mylava on 2016/6/29.
 */
public class SetData {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.130:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
//        client.delete().deletingChildrenIfNeeded().forPath(path).;
        client.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());

        Thread.sleep(5000);

        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        System.out.println("Success set node for: "+ path +", new version: "+client.setData()
        .withVersion(stat.getVersion()).forPath(path).getVersion());

        try {
            client.setData().withVersion(stat.getVersion()).forPath(path);
        } catch (Exception e) {
            System.out.println("Fail set node due to :"+ e.getMessage());
        }


    }

}
