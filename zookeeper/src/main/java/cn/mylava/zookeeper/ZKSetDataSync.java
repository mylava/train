package cn.mylava.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/27.
 */
public class ZKSetDataSync implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String path = "/zk-book";
        zooKeeper = new ZooKeeper("192.168.159.129:2181",5000,new ZKSetDataSync());
        connectedSemaphore.await();

        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getData(path,true,null);
        // -1 表示对最新版本进行更新操作，用于对zookeeper数据节点的更新没有原子性要求的操作
        Stat stat = zooKeeper.setData(path,"456".getBytes(),-1);
        System.out.println(stat.getCzxid()+","+ stat.getMzxid()+","+stat.getVersion());

        Stat stat1 = zooKeeper.setData(path,"456".getBytes(),stat.getVersion());
        System.out.println(stat1.getCzxid()+","+ stat1.getMzxid()+","+stat1.getVersion());

        try {
            //因为这里还是使用stat的数据版本（陈旧版本），所以更新失败了
            zooKeeper.setData(path,"456".getBytes(),stat.getVersion());
        } catch (KeeperException e) {
            System.out.println(e.code()+","+e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

}
