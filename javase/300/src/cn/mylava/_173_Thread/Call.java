package cn.mylava._173_Thread;

import java.util.concurrent.*;

/**
 * 使用Callable创建线程
 * 1.创建Callable实现类 重写call()
 * 2.借助执行调度服务ExecutorService获取Future对象
 * 3.从Future对象中获取返回值
 * 4.停止服务
 *
 * 两个优点   1).可以返回值   2).可以对外声明异常
 *
 * Created by lipengfei on 2017/3/16.
 */
public class Call {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程
        ExecutorService service = Executors.newFixedThreadPool(1);
        Race tortoise = new Race("乌龟",1000);
        Race rabbit = new Race("兔子",500);
        //获取值
        Future<Integer> result1 = service.submit(tortoise);
        Future<Integer> result2 = service.submit(rabbit);

        Thread.sleep(20000);
        tortoise.setFlag(false);
        rabbit.setFlag(false);

        int num1 = result1.get();
        int num2 = result2.get();


        System.out.println("乌龟跑了-->"+num1+"步");
        System.out.println("兔子跑了-->"+num2+"步");
        //停止服务
        service.shutdownNow();


    }

}
//创建Callable实现类
class Race implements Callable<Integer> {
    private String name; //名称
    private long time;   //两步之间间隔的时间   类比 网购付款时候等待的时间
    private boolean flag = true;
    private int step = 0; //步数

    public Race() {
    }

    public Race(String name, long time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public Integer call() throws Exception {
        while (flag) {
            Thread.sleep(time); //两步之间间隔的时间
            step++;
        }
        return step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
