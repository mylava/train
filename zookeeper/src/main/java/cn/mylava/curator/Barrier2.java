package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by mylava on 2016/6/30.
 */
public class Barrier2 {
    static String path = "/curator_barrier_path";

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder()
                                .connectString("192.168.159.129:2181")
                                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
                        client.start();
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client,path,5);
                        Thread.sleep(Math.round(Math.random()*3000));
                        System.out.println(Thread.currentThread().getName()+"号进入barrier");
                        //调用enter方法后进入等待，此时处于准备进入状态。一旦准备进入Barrier的成员数达到5个后，所有成员会被同时出发进入。
                        barrier.enter();

                        System.out.println("启动。。。");

                        Thread.sleep(Math.round(Math.random()*3000));
                        //调用leave方法会再次进入等待，此时处于准备退出状态。一旦准备退出Barrier的成员数达到5个后，所有成员同样会被同时出发退出。
                        barrier.leave();
                        System.out.println("退出。。。");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
