package cn.mylava.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/24.
 */
public class ZKCcreateAsync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.159.130:2181",5000,
                new ZKCcreateAsync());
        connectedSemaphore.await();

        zk.create("/zk-test-createByJava_asyn","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new StringCallback(),"I am context.");
        zk.create("/zk-test-createByJava_asyn","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new StringCallback(),"I am context.");
        zk.create("/zk-test-createByJava_asyn","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                new StringCallback(),"I am context.");

        Thread.sleep(Integer.MAX_VALUE);
    }
}

class StringCallback implements AsyncCallback.StringCallback {

    public void processResult(int resultCode, String path, Object ctx, String name) {
        System.out.println("Create Path result: [" + resultCode + "," + path + "," + ctx +
        ", real Path name: " + name);
    }
}
