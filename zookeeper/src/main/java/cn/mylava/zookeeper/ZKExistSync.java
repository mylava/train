package cn.mylava.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 *  1.无论节点是否存在，通过exist接口都可以注册wathcer
 *  2.exist接口中注册的wathcer，能够对节点创建、节点删除和节点更新事件进行监听。
 */
public class ZKExistSync implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public void process(WatchedEvent event) {
        try {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (event.getType() == Event.EventType.None && null == event.getPath()) {
                    System.out.println("节点为空");
                    connectedSemaphore.countDown();
                } else if (Event.EventType.NodeCreated == event.getType()) {
                    System.out.println("Node (" + event.getPath() + ") Created");
                    zooKeeper.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDeleted == event.getType()) {
                    System.out.println("Node (" + event.getPath() + ") Deleted");
                    zooKeeper.exists(event.getPath(),true);
                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    System.out.println("Node (" + event.getPath() + ") DataChanged");
                    zooKeeper.exists(event.getPath(),true);
                }

            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book";
        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000,new ZKExistSync());
        connectedSemaphore.await();

        //chance是否存在指定节点，并注册一个watcher
        zooKeeper.exists(path,true);
        //创建节点，此时，服务端会向客户端发送事件通知：NodeCreated，客户端在收到通知后，再次调用exists方法，同时再注册一个watcher
        zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        //更新节点数据，服务端再向客户端发送事件通知：NodeDataChanged，客户端接收通知后，继续调用exist方法，同时注册watcher
        zooKeeper.setData(path,"123".getBytes(),-1);
        //创建子节点
        zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        //删除子节点
        zooKeeper.delete(path+"/c1",-1);
        //删除节点，服务端向客户端发送事件通知：NodeDeleted
        zooKeeper.delete(path,-1);
        Thread.sleep(Integer.MAX_VALUE);

    }
}
