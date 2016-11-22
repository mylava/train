package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mylava on 2016/6/29.
 *
 * 在ZooKeeper中，所有异步通知事件处理都是由EventThread这个线程来处理的。
 * EventThread线程用于串行处理所有的事件通知，一旦遇到复杂的处理单元，会消耗过长的处理时间，从而影响其他事件的处理。
 * 因此inBackground接口允许用户传入一个Executor实例，这样就可以把比较复杂的事件处理放到一个专门的线程池中去。
 *
 */
public class CreateNodeAsync {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.129:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();
    static CountDownLatch semaphore = new CountDownLatch(2);
    static ExecutorService tp = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println("Main thread:"+ Thread.currentThread().getName());

        /**
         * 传入ExecutorService，这样Curator的异步事件处理逻辑就会交给该线程池去做。
         */
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("event[code: "+event.getResultCode()+", type: " + event.getType()+"]");
                        System.out.println("Thread of processResult: "+Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                },tp).forPath(path,"init".getBytes());

        /**
         * 没有传入Executor，因此使用ZooKeeper默认的EventThread处理。
         */
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("event[code: "+event.getResultCode()+", type: " + event.getType()+"]");
                        System.out.println("Thread of processResult: "+Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                }).forPath(path,"init".getBytes());

        semaphore.await();
        tp.shutdown();
    }

}
