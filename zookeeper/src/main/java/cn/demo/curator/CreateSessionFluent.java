package cn.demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用fluent风格的API接口来创建一个ZooKeeper客户端
 */
public class CreateSessionFluent {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.159.130:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}
