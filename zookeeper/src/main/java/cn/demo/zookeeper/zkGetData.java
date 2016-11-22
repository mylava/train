package cn.demo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 获取ZNode的数据
 */
public class zkGetData implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    //用来存储ZNode的Stat信息
    private static Stat stat = new Stat();

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) { //建立连接成功
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) { //节点数据变更时间
                try {
                    System.out.println("节点被修改，data为: "+new String(zooKeeper.getData(event.getPath(),true,stat)));
                    System.out.println("节点被修改，Stat数据为: "+stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 同步获取节点数据
     * @throws Exception
     */
    public static void getDataSync() throws Exception {
        String path = "/zk-test";

        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000,new zkGetData());
        connectedSemaphore.await();

        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        //同步获取数据，并注册Watcher
        System.out.println("同步获取到数据: "+new String(zooKeeper.getData(path,true,stat)));
        System.out.println("同步获取到状态: "+stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());

        zooKeeper.setData(path,"123".getBytes(),-1);

        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 异步获取节点数据
     * @throws Exception
     */
    public static void getDataAsync() throws Exception {
        String path = "/zk-test";
        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000,new zkGetData());
        connectedSemaphore.await();
        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        //异步获取数据，并注册Watcher
        zooKeeper.getData(path, true, new DataCallback(),null);
        //修改数据，触发Watcher事件
        zooKeeper.setData(path,"123".getBytes(),-1);
        Thread.sleep(Integer.MAX_VALUE);
    }



    public static void main(String[] args) throws Exception {
//        getDataSync();
        getDataAsync();
    }

}

/**
 * 异步获取数据的回调
 */
class DataCallback implements  AsyncCallback.DataCallback {
    public void processResult(int resultCode, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println("异步获取到数据："+resultCode+","+path+","+new String(data));
        System.out.println("异步获取到状态："+stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
    }
}