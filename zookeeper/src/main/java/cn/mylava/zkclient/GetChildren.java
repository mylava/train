package cn.mylava.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 1.客户端可以对一个不存在的节点进行子节点变更的监听。
 * 2.一旦客户端对一个节点注册了子节点变更监听之后，当子节点发生变更时，服务端都会通知客户端，并将最新的子节点列表发送给客户端。
 * 3.该节点本身的创建或删除也会通知到客户端。
 */
public class GetChildren {

    public static void main(String[] args) throws InterruptedException {
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient("192.168.159.130:2181",5000);

        //订阅子节点变动，需要自己实现Listener接口，与ZooKeeper提供的原生Watcher不同的是，ZkListener是永久的，只需要注册一次就会一直生效。
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(parentPath+"'s child changed,current Children is: "+currentChilds);
            }
        });

        zkClient.createPersistent(path);
        Thread.sleep(3000);
        System.out.println(zkClient.getChildren(path));
        Thread.sleep(3000);

        zkClient.createPersistent(path+"/c1");
        Thread.sleep(3000);
        zkClient.delete(path+"/c1");
        Thread.sleep(3000);

        zkClient.delete(path);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
