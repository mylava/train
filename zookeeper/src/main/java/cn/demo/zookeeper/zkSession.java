package cn.demo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 创建ZooKeeper会话示例
 */
public class zkSession implements Watcher {
    //CountDownLatch累使一个倒计数的锁存器，构造时传入int参数，该参数就是计数器的初始值，每调用一次countDown()方法，计数器减1，计数器大于0时，await()方法会阻塞程序继续执行。
    private static CountDownLatch connectedSemaphore = new CountDownLatch(2);

    public void process(WatchedEvent event) {
        System.out.println("Receive watched event：" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            //计数器减一
            connectedSemaphore.countDown();
        }
    }


    /**
     * 创建ZooKeeper会话
     * @throws IOException
     */
    public static void testConstructor() throws IOException {
        /*
         *   ZooKeeper客户端和服务端会话的建立是一个异步过程，构造方法在处理完客户端初始化工作后立即返回。
         *   大多数情况下，此时并没有真正建立好一个可用的会话，在会话的生命周期中处于CONNECTING状态。
         *   当会话真正创建完毕后，ZooKeeper服务端会向对应的客户端发送一个事件通知，客户端只有在获取这个通知后，才算真正建立了会话。
         *   特别说明：sessionTimeout，在一个会话周期内，客户端和服务器之间通过心跳检测机制来维护会话的有效性，一旦在sessionTimeout时间内没有进行有效心跳检测，会话就会失效。
         */
        ZooKeeper zk = new ZooKeeper("192.168.159.128:2181",5000, new zkSession());
        System.out.println("State============"+zk.getState());
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Zookeeper session established.");
    }

    /**
     * 创建可复用的ZooKeeper会话
     * @throws IOException
     */
    public static void testConstructorWithSession() throws IOException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("192.168.159.128:2181",5000, new zkSession());

        //sessionId初始值是0
        long sessionId = zk.getSessionId();
        byte[] pwd = zk.getSessionPasswd();
        //随意输入一个sessionID 和 password
//        zk = new ZooKeeper("192.168.159.128:2181",5000,new zkSession(), 1L,"password".getBytes());
        //正确的sessionId
        ZooKeeper zk1 = new ZooKeeper("192.168.159.128:2181",5000,new zkSession(), sessionId,pwd);

        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State============"+zk.getState());
        System.out.println("State============"+zk1.getState());
    }


    public static void main(String[] args) throws Exception {
//        testConstructor();
        testConstructorWithSession();
    }

}
