package cn.demo.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 检测节点是否存在
 */
public class zkExisits implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public void process(WatchedEvent event) {
        try {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (event.getType() == Event.EventType.None && null == event.getPath()) {
                    System.out.println("节点为空");
                    connectedSemaphore.countDown();
                } else if (Event.EventType.NodeCreated == event.getType()) {
                    System.out.println("节点 (" + event.getPath() + ") 被创建");
                    zooKeeper.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDeleted == event.getType()) {
                    System.out.println("节点 (" + event.getPath() + ") 被删除");
                    zooKeeper.exists(event.getPath(),true);
                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    System.out.println("节点 (" + event.getPath() + ") 数据被更新");
                    zooKeeper.exists(event.getPath(),true);
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步检测节点是否存在
     * @throws Exception
     */
    public static void exisitsSync() throws Exception {
        String path = "/zk-test";
        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000,new zkExisits());
        connectedSemaphore.await();

        //检测是否存在指定节点，并注册一个watcher
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
