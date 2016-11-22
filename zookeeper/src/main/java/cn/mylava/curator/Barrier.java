package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by mylava on 2016/6/29.
 */
public class Barrier {
    static String path = "/curator_barrier_path";
    static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder()
                                .connectString("192.168.159.129:2181")
                                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

                        client.start();
                        barrier = new DistributedBarrier(client, path);
                        System.out.println(Thread.currentThread().getName() + "号barrier设置");
                        //设置barrier
                        barrier.setBarrier();
                        //等待barrier释放
                        barrier.waitOnBarrier();
                        System.err.println(Thread.currentThread().getName() + " 启动");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        barrier.removeBarrier();
    }

}
