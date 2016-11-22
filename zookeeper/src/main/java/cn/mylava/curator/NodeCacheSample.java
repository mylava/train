package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by mylava on 2016/6/29.
 *
 * NodeCache不仅可以用于监听数据节点的内容变更，也能监听指定节点是否存在。
 * 如果原本节点不存在，Cache就会在节点被创建之后触发NodeCacheListener。
 * 但是，如果该数据节点被删除，那么Curator就无法触发NodeCacheListener了。
 */
public class NodeCacheSample {
    static String path = "/zk-book/nodecache";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.129:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());
        /**
         * NodeCache(CuratorFramework client, String path, boolean dataIsCompressed)
         * client 客户端实例
         * path 监听的数据节点路径
         * dataIsCompressed  是否进行数据压缩
         */
        final NodeCache cache = new NodeCache(client,path,false);
        //默认为false，如果设置为true，那么NodeCache在第一次启动的时候就会立刻从ZooKeeper上读取对应节点的数据内容，并保存在Cache中。
        cache.start(true);

        //NodeCache定义了事件处理的回调接口NodeCacheListener,这里实现该接口，完成对事件的处理
        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.println("Node Data updata, new data: "+new String(cache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path,"u".getBytes());
        Thread.sleep(1000);
        client.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(1000);
    }
}
