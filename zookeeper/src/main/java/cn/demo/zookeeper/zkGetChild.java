package cn.demo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 获取ZNode的子节点列表
 */
public class zkGetChild implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            //
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();

            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {//子节点变更，获取子节点的node
                try {
                    System.out.println("子节点变更为:"+zooKeeper.getChildren(event.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 同步获取节点列表---------只能获取节点路径
     * @throws Exception
     */
    public static void getChildSync() throws Exception {
        String path = "/zk-test";
        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000, new zkGetChild());
        connectedSemaphore.await();
        //创建永久节点和临时节点
        zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        //获取节点列表，并注册一个Watcher，true表示使用默认Watcher，false表示不需要注册Watcher
        List<String> childrenList = zooKeeper.getChildren(path,true);
        System.out.println("list ========================="+childrenList);
        System.out.println("创建新的ZNode，触发Watcher================");
        //创建新的ZNode，触发Watcher
        zooKeeper.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

        Thread.sleep(Integer.MAX_VALUE);
    }
    /**
     * 异步获取节点列表-------------获取节点路径和状态信息
     * @throws Exception
     */
    public static void  getChildrenAsync() throws Exception {
        String path = "/zk-test";
        zooKeeper = new ZooKeeper("192.168.159.128:2181",5000, new zkGetChild());
        connectedSemaphore.await();
//        zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

        zooKeeper.getChildren(path,true,new Children2Callback(),null);
        zooKeeper.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

        Thread.sleep(Integer.MAX_VALUE);
    }


    public static void main(String[] args) throws Exception {
//        getChildSync();
        getChildrenAsync();
    }
}

/**
 * 异步获取子节点回调
 */
class Children2Callback implements AsyncCallback.Children2Callback {
    public void processResult(int resultCode, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("获取节点信息及子节点列表信息: [response code: "+resultCode+",param path: "+path
                +", ctx: "+ctx+", children list: "+ children +",\n stat: { "+stat+" }]");
    }
}