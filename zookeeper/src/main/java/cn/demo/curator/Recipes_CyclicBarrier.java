package cn.demo.curator;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用JDK自带的CyclicBarrier演示赛跑比赛
 * 在同一个JVM中的话，多线程并发会等待所有线程都处于就绪状态后才开始同时执行其他业务逻辑。
 */
public class Recipes_CyclicBarrier {

    public static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread(new Runner("1号选手")));
        executor.submit(new Thread(new Runner("2号选手")));
        executor.submit(new Thread(new Runner("3号选手")));
        executor.shutdown();
    }
}

class Runner implements Runnable{
    private String name;

    public Runner(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name+" 准备好了。");
        try {
            Recipes_CyclicBarrier.barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name + "起跑！");
    }
}