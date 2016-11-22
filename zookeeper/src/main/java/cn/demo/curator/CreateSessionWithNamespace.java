package cn.demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 创建含隔离命名空间的会话
 *
 * 为了实现不同的ZooKeeper业务之间的隔离，往往会为每个业务分配一个独立的命名空间，即指定一个ZooKeeper根路径。
 * 创建之后，该客户端对服务器上数据节点的所有操作，都是基于该相对目录进行的
 */
public class CreateSessionWithNamespace {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.159.128:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();

        client.start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}
