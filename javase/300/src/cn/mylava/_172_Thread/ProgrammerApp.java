package cn.mylava._172_Thread;

/**
 * 推荐 Runnable 创建线程
 *  1).避免单继承的局限性
 *  2).便于共享资源
 *  @see Web12306
 *
 *
 * Created by lipengfei on 2017/3/16.
 */
public class ProgrammerApp {
    public static void main(String[] args) {
        //1).创建真实角色
        Programmer programmer = new Programmer();
        //2).创建代理角色 + 真实角色引用
        Thread proxy = new Thread(programmer);
        // 3).调用start()启动线程
        proxy.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("一边聊qq......");
        }
    }
}
