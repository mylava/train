package cn.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * curator子节点监听
 *
 * 对某个节点进行子节点时间的监听
 * 对节点本身的变更不会通知到客户端，也无法对二级子节点进行监听
 */
public class ChildrenCache {
    static String path = "/zk-curator";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.128:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        /**
         * @param client           the client
         * @param path             path to watch
         * @param cacheData        if true, node contents are cached in addition to the stat  配置为true，客户端在接收到节点列表变更的同时，也能获取到节点的数据内容
         * @param dataIsCompressed if true, data in the path is compressed
         * @param threadFactory    factory to use when creating internal threads
         * @param executorService  ExecutorService to use for the PathChildrenCache's background thread. This service should be single threaded, otherwise the cache may see inconsistent results.
         *                         组合使用threadFactory和executorService两个参数，可以构造一个专门的线程池来处理事件通知
         */
        final PathChildrenCache cache = new PathChildrenCache(client,path,true);

        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        //子节点变更
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADD, "+event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATE, "+ event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED, " + event.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });

        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        Thread.sleep(1000);
        client.create().withMode(CreateMode.PERSISTENT).forPath(path+"/c1");
        Thread.sleep(1000);
        client.delete().forPath(path+"/c1");
        Thread.sleep(1000);
        //节点本身的变更并没有通知到客户端
        client.delete().forPath(path);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
