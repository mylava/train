package cn.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 选举Master示例
 */
public class MasterSelect {
    static String master_path = "/curator_recipes_master_path";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.129:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

    public static void main(String[] args) throws InterruptedException {
        client.start();
        LeaderSelector selector = new LeaderSelector(client, master_path,
                //成功获取Master权利后回调LeaderSelectorListenerAdapter监听器
                new LeaderSelectorListenerAdapter() {
                    //一旦执行完takeLeadership方法，Curator就会立即释放Master权利，然后重新开始新一轮的Master选举
                    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                        System.out.println("成为Master角色");
                        System.out.println("业务处理进行中……");
                        Thread.sleep(3000);
                        System.out.println("完成Master操作，释放Master权利");
                    }
                });
        //takeLeadership执行完之后，是否加入选举，默认为false
        selector.autoRequeue();
        //开始参与Master选举
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
