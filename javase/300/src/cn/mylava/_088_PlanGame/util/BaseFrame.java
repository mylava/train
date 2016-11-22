package cn.mylava._088_PlanGame.util;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 所有游戏窗口的父类
 */
public class BaseFrame extends Frame{

    /**
     * 加载窗口
     */
    public void launchFrame(){
        setSize(Constant.WIDTH,Constant.HEIGHT);
        setLocation(100, 100);
        setVisible(true);

        new PaintThread().start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    /**
     * 定义一个重画窗口的线程类
     * 使用内部类,方便调用外部类的reapint()方法.
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 图片缓冲
     */
    private Image offScreenImage = null;
    public void update(Graphics g){
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.WIDTH,Constant.HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();

        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }
}
