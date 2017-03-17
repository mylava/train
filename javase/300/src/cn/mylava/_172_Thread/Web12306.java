package cn.mylava._172_Thread;

/**
 * 演示  使用Runnable 创建线程，便于共享资源
 *
 * Created by lipengfei on 2017/3/16.
 */
public class Web12306 implements Runnable{
    //剩余票数
    private int num = 5000;

    @Override
    public void run() {
        while (true) {
            if (num <= 0) {
                break;
            }
            System.out.println(Thread.currentThread().getName()+"抢到了"+num--);
        }
    }

    public static void main(String[] args) {
        //真是角色Web12306
        Web12306 web = new Web12306();
        //代理
        Thread t1 = new Thread(web,"黄牛A");
        Thread t2 = new Thread(web,"黄牛B");
        Thread t3 = new Thread(web,"民工C");
        //启动线程
        t1.start();
        t2.start();
        t3.start();


    }
}
