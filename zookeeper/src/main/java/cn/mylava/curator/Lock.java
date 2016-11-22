package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/29.
 *
 * 实现思路：指定一个ZooKeeper数据节点作为计数器，多个应用实例在分布式锁的控制下，通过更新该节点的内容来实现技术功能。
 */
public class Lock {
    static String path = "/curator_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.130:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

    public static void main(String[] args) {
        client.start();
        final InterProcessLock lock = new InterProcessMutex(client,path);
        final CountDownLatch semaphore = new CountDownLatch(1);
        for (int i=0;i<30;i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        semaphore.await();
                        /**
                         * Acquire the mutex - blocking until it's available. Each call to acquire must be balanced by a call
                         */
                        lock.acquire();
                    } catch (Exception e) {
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单号是："+orderNo);

                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        semaphore.countDown();
    }

}
