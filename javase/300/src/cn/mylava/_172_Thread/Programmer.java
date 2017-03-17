package cn.mylava._172_Thread;

/**
 * 模拟程序员工作------实现Runnable接口实现多线程
 *
 * 1.类实现Runnable 接口，重写run()  -->真实角色类
 * 2.启动多线程  使用静态代理   ——> Thread为代理角色类
 *  1).创建真实角色
 *  2).创建代理角色 + 真实角色引用
 *  3).调用start()启动线程
 * Created by lipengfei on 2017/3/16.
 */
public class Programmer implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("一边敲代码......");
        }
    }
}
