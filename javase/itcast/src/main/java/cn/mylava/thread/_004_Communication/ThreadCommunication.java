package cn.mylava.thread._004_Communication;

/**
 * 线程通信
 *
 * 问题：子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，接着再回到主线程循环100次，
 * 如此循环50次。
 *
 * Created by lipengfei on 2017/3/11.
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i=1;i<=50;i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i=1;i<=50;i++) {
            business.main(i);
        }
    }

}


