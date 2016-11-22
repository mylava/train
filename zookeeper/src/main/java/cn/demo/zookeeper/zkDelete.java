package cn.demo.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 删除ZNode示例
 */
public class zkDelete implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static String path = "/zk-test";
    private static ZooKeeper zk;
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }

    /**
     *同步删除节点
     * @throws Exception
     */
    public static void deleteSync() throws Exception {
        zk = new ZooKeeper("192.168.159.129:2181",5000,new zkDelete());
        connectedSemaphore.await();
        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.delete(path,-1);
        Thread.sleep(Integer.MAX_VALUE);
    }
    /**
     *异步删除节点
     * @throws Exception
     */
    public static void deleteAsync() throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.159.129:2181",5000,new zkDelete());
        connectedSemaphore.await();
        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.delete(path, -1, new AsyncCallback.VoidCallback() {
            public void processResult(int resultCode, String path, Object ctx) {
                System.out.println("Create Path result: [" + resultCode + "," + path + "," + ctx +"]");
            }
        },"this is a context");

        Thread.sleep(Integer.MAX_VALUE);
    }


    public static void main(String[] args) throws Exception {
//        deleteSync();
        deleteAsync();
    }
}
