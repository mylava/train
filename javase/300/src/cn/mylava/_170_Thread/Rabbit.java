package cn.mylava._170_Thread;

/**
 * 模拟龟兔赛跑------继承Thread类实现多线程
 * 1.创建线程：  继承Thread   重写run（线程体）
 * 2.使用线程：  创建子类对象  调用start方法   线程启动
 * Created by lipengfei on 2017/3/12.
 */
public class Rabbit extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("兔子跑了"+ i +"步");
        }
    }
}

class Tortoise extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            System.out.println("乌龟跑了"+ i +"步");
        }
    }

}