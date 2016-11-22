package cn.mylava.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * Created by mylava on 2016/6/29.
 */
public class DistAtomicInt {
    static String path = "/curator_distatomicint_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.159.130:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        DistributedAtomicInteger distributedAtomicInteger = new DistributedAtomicInteger(client,path,new RetryNTimes(3,1000));
        AtomicValue<Integer> rc = distributedAtomicInteger.add(8);
        System.out.println("Result: "+rc.succeeded());

    }
}
