package cn.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by mylava on 2016/7/27.
 */
public class OperateNode {
    static String path = "/zk-curator/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.128:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        //创建节点
        client.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());

        Stat stat = new Stat();
        //获取节点数据
        String data = new String(client.getData().storingStatIn(stat).forPath(path));
        System.out.println("data===================="+data);
        System.out.println("stat===================="+stat);
        //更新节点数据
        client.setData()
                .withVersion(stat.getVersion()).forPath(path,"test".getBytes());
        data = new String(client.getData().storingStatIn(stat).forPath(path));
        System.out.println("data===================="+data);
        System.out.println("stat===================="+stat);

        Thread.sleep(5000);
        //删除节点
        client.delete()
                //汲联删除
                .deletingChildrenIfNeeded()
                //版本号
                .withVersion(stat.getVersion())
                //节点路径
                .forPath(path);
    }
}
