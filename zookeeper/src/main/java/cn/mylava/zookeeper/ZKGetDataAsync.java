package cn.mylava.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/27.
 */
public class ZKGetDataAsync implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book";
        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000,new ZKGetDataAsync());
        connectedSemaphore.await();
        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        zooKeeper.getData(path, true, new DataCallback(),null);
        zooKeeper.setData(path,"123".getBytes(),-1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                zooKeeper.getData(event.getPath(), true, new DataCallback(),null);
            }
        }
    }
}
class DataCallback implements  AsyncCallback.DataCallback {
    public void processResult(int resultCode, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println(resultCode+","+path+","+new String(data));
        System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
    }
}
