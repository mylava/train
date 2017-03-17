package cn.mylava.thread._004_Communication;

/**
 * 1.通过在方法上添加synchroinzed实现方法的互斥，保证在一次循环期间不会相互掺杂输出
 *
 * 经验：要用到共同数据（比如同步锁）的若干个方法应该归在同一个类身上，这种设计体现了程序的高内聚。
 *
 * 2.定义一个变量subTurn，
 *
 * Created by lipengfei on 2017/3/11.
 */
public class Business {

    private boolean subTurn = true;


    public synchronized void sub(int n) {
        while (!subTurn) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println("sub thread sequece of " + i + ", loop of " + n);
        }

        subTurn = false;
        this.notify();
    }

    public synchronized void main(int n) {
        while (subTurn) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i=1; i<=100; i++) {
            System.out.println("main thread sequece of "+ i + ", loop of "+n);
        }

        subTurn = true;
        this.notify();
    }
}
