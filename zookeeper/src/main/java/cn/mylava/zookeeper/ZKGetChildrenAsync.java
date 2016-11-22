package cn.mylava.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/25.
 */
public class ZKGetChildrenAsync implements Watcher{
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("ReGet child:" + zooKeeper.getChildren(event.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book";
        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000,
                new ZKGetChildrenAsync());
        connectedSemaphore.await();
//        zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        zooKeeper.getChildren(path,true,new Children2Callback(),null);
        zooKeeper.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

        Thread.sleep(Integer.MAX_VALUE);

    }
}

class Children2Callback implements AsyncCallback.Children2Callback {
    public void processResult(int resultCode, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("Get Children znode result: [response code: "+resultCode+",param path: "+path
        +", ctx: "+ctx+", children list: "+ children +", stat: "+stat);
    }
}
