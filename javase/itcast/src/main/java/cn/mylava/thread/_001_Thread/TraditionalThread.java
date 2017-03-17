package cn.mylava.thread._001_Thread;

/**
 * 传统线程
 * Created by lipengfei on 2017/3/10.
 */
public class TraditionalThread {
    public static void main(String[] args) {
        /** 实现多线程方式1，继承Thread类实现 */
        //匿名内部类实现run方法
        Thread t1 = new Thread() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1:"+ currentThread().getName());
                    System.out.println("2:"+ this.getName());
                }
            }
        };
        //线程在start的时候要执行一段代码，所执行的代码就是Thread类里边的run方法。
        t1.start();



        /** 实现多线程方式2，实现Runnable接口实现 */
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3:"+ Thread.currentThread().getName());
                }
            }
        });
        t2.start();

        /**
         * 思考会执行哪段代码？ 为什么？
         * 提示： 子类实现父类方法，即从重写入手。
         **/
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("4:"+ Thread.currentThread().getName());
                }
            }
        }){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("5:"+ currentThread().getName());
                }

            }
        }.start();

    }
}
