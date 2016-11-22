package cn.mylava.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/24.
 */
public class ZKConstructorWithPassword implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public void process(WatchedEvent event) {
        System.out.println("Receive watched event：" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("192.168.159.128:2181",5000,new ZKConstructorWithPassword());
        connectedSemaphore.await();

        long sessionId = zk.getSessionId();
        byte[] pwd = zk.getSessionPasswd();

        zk = new ZooKeeper("192.168.159.128:2181",5000,new ZKConstructorWithPassword(),
                1l,"password".getBytes());


        zk = new ZooKeeper("192.168.159.128:2181",5000,new ZKConstructorWithPassword(),
                sessionId,pwd);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
