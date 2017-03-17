package cn.mylava.thread._003_Synchronized;

/**
 * 线程的互斥
 *
 * 静态方法所使用的同步监视器对象是所在类的字节码对象
 *
 * Created by lipengfei on 2017/3/10.
 */
public class ThreadSynchronized {
    public static void main(String[] args) {
        //声明外部类对象
        new ThreadSynchronized().init();
    }

    private void init() {
        //构造内部类对象必须有外部类对象
        final Outputer outputer = new Outputer();
        //两个线程同时访问一个对象
        //线程1
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("张三");
                }
            }
        }).start();
        //线程2
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("李四");
                }
            }
        }).start();
    }

    class Outputer {

        //synchronized放在方法体上，默认的同步监视器对象就是this
        public synchronized void output(String name) {
            int len = name.length();
            //synchronized放在主代码块上
            synchronized (this) {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();

            }
        }


        //synchronized放在主代码块上
        /*
        public void output(String name) {
            int len = name.length();
            synchronized (this) {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();

            }
        }
        */
    }
}
