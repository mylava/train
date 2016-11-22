package cn.mylava.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/25.
 */
public class ZKGetDataSync implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("NodeDataChanged: "+new String(zooKeeper.getData(event.getPath(),true,stat)));
                    System.out.println("NodeDataChanged: "+stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());

                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
        String path = "/zk-book";

        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000,
                new ZKGetDataSync());
        connectedSemaphore.await();

        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

        System.out.println("main: "+new String(zooKeeper.getData(path,true,stat)));
        System.out.println("main: "+stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());

        zooKeeper.setData(path,"123".getBytes(),-1);

        Thread.sleep(Integer.MAX_VALUE);

    }
}
