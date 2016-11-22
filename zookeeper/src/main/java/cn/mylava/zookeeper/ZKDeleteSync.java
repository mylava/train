package cn.mylava.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/24.
 */
public class ZKDeleteSync implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zk;
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book";

        zk = new ZooKeeper("192.168.159.129:2181",5000,new ZKDeleteSync());
        connectedSemaphore.await();
        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.delete(path,-1);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
