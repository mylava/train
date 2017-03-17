package cn.mylava.thread._002_Timer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 * Created by lipengfei on 2017/3/10.
 */
public class TraditionalTimer {
    public static void main(String[] args) {
        // 声明一个定时器，第一个参数是定时任务TimerTask，第二个参数是首次触发的延时，第三个参数是后续触发的延时
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" \t bombing");
            }
        },10000,3000);

        //  每隔一秒打印当前秒数
        while (true) {
            System.out.println(Thread.currentThread().getName()+ "\t" +Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
