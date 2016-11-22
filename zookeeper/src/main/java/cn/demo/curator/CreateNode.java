package cn.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 创建节点示例
 */
public class CreateNode {
    static String path = "/zk-curator/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.130:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                //递归创建
                .creatingParentContainersIfNeeded()
                //节点类型
                .withMode(CreateMode.EPHEMERAL)
                //创建带有data的节点
                .forPath(path, "init".getBytes());
    }
}
