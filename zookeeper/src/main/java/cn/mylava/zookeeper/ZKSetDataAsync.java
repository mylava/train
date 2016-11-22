package cn.mylava.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/28.
 */
public class ZKSetDataAsync implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (event.getType() == Event.EventType.None && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book";
        zooKeeper = new ZooKeeper("192.168.159.129:2181",5000,new ZKSetDataAsync());
        connectedSemaphore.await();
        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        zooKeeper.setData(path,"456".getBytes(),-1,new StatCallback(),null);

        Thread.sleep(Integer.MAX_VALUE);
    }
}

class StatCallback implements AsyncCallback.StatCallback {

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (rc == 0) {
            System.out.println("Success!");
        }
    }
}