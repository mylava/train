package cn.mylava.curator;

import com.sun.org.apache.bcel.internal.generic.DADD;
import com.sun.org.apache.xml.internal.security.Init;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mylava on 2016/6/29.
 */
public class NoLock {
    public static void main(String[] args) {
        final CountDownLatch semaphore = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        semaphore.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.err.println("生成的订单号是："+orderNo);
                }
            }).start();
        }
        semaphore.countDown();
    }
}
