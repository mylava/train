package cn.mylava.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by mylava on 2016/6/28.
 */
public class CreateSession {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 默认主要有四种实现，分别是 ExponentialBackoffRegry,RetryNTimes,RetryOneTime,RetryUntilElapsed
         */
        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3); //初始sleep时间，最大重试次数

        /**
         * public static org.apache.curator.framework.CuratorFramework newClient(
            java.lang.String connectString, --------- host:port
            int sessionTimeoutMs,   ----------会话超时时间 默认60000
            int connectionTimeoutMs, -----------连接创建超时时间 默认15000
            org.apache.curator.RetryPolicy retryPolicy -----------重试策略
         )
         */
        //通过静态工厂创建客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.159.130:2181",5000,3000,retryPolicy);
        //通过start方法创建会话
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
