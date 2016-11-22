package cn.demo.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 创建ZNode示例.
 */
public class zkCreate implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }


    /**
     * 同步创建ZNode
     * @throws Exception
     */
    public static void createSync() throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.159.129:2181",5000, new zkCreate());
        connectedSemaphore.await();
        //创建临时节点
        String path1 = zk.create("/zk-test-createByJava","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println("Success create znode: "+path1);
        //创建临时顺序节点
        String path2 = zk.create("/zk-test-createByJava","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: " + path2);

        System.out.println();
    }

    /**
     * 异步创建ZNode
     * @throws Exception
     */
    public static void createAsync() throws Exception {
        //创建会话
        ZooKeeper zk = new ZooKeeper("192.168.159.130:2181",5000, new zkCreate());
        connectedSemaphore.await();
        String path = "/zk-test-createByJava_asyn";
        //cb 注册一个异步回调函数。需要实现StringCallback接口，主要是对processResult方法的重写，节点创建完毕后，客户端自动调用这个方法处理相关的业务逻辑。
        //ctx 用于传递一个对象，可以在回调方法执行的时候使用，通常是放一个上下文信息。
        zk.create(path,"".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new StringCallback(),"I am context.");
        zk.create(path,"".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new StringCallback(),"I am context.");
        zk.create(path,"".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                new StringCallback(),"I am context.");
        //延迟会话失效时间
        Thread.sleep(3000);
    }


    public static void main(String[] args) throws Exception {
//        createSync();
        createAsync();
    }

}
class StringCallback implements AsyncCallback.StringCallback {
    /**
     * 在回调函数中处理相关的业务
     * @param resultCode  服务端响应码  0表示调用成功，-4客户端和服务端连接已断开，-110指定节点已存在，-112会话已过期
     * @param path  传入的节点路径参数
     * @param ctx
     * @param name   实际在服务端创建的节点名
     */
    public void processResult(int resultCode, String path, Object ctx, String name) {
        System.out.println("Create Path result: [" + resultCode + "," + path + "," + ctx +
                ", real Path name: " + name);
    }
}
