package cn.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁示例
 */
public class RecipesDemo {

    /**
     * 模拟分布式环境中使用时间戳生成流水号
     */
    public static void recipesNoLock(){
        final CountDownLatch semaphore = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        semaphore.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.err.println("生成的订单号是："+orderNo);
                }
            }).start();
        }
        semaphore.countDown();
    }

    /**
     * 使用分布式锁生成流水号
     */
    public static void recipesLock(){
        String path = "/curator_lock_path";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.159.130:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

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
                         * 获取锁
                         */
                        lock.acquire();
                    } catch (Exception e) {
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单号是："+orderNo);

                    try {
                        //释放锁
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        semaphore.countDown();
    }

    public static void main(String[] args) {
        recipesNoLock();
        recipesLock();
    }

}
