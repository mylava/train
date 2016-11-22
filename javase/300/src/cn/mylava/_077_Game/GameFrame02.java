package cn.mylava._077_Game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 实现窗口物体沿着垂直和水平移动
 */
public class GameFrame02 extends Frame{
    /**物体的坐标*/
    private double x=100,y=100;
    private boolean left;
    private boolean up;

    Image img = GameUtil.getImage("images/sun.jpg");

    /**
     * 加载窗口
     */
    public void launchFrame(){
        setSize(500,500);
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

    public static void main(String[] args) {
        GameFrame02 gf = new GameFrame02();
        gf.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img,(int)x,(int)y,null);
        if (left){
            x-=10;
        }else {
            x+=10;
        }
        if (x>500-30){
            left = true;
        }
        if (x<=0){
            left = false;
        }

        if (up){
            y-=10;
        }else {
            y+=10;
        }
        if (y>500-30){
            up = true;
        }
        if (y<=30){
            up = false;
        }

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
}
