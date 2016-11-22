package cn.mylava._077_Game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 窗口物体沿着任意角度飞行
 */
public class GameFrame03 extends Frame{
    /**物体的坐标*/
    private double x=100,y=100;
    private double degree = 3.14/3;
    private double speed = 10;

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
        GameFrame03 gf = new GameFrame03();
        gf.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img,(int)x,(int)y,null);


        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);
        if(y>500-30 || y<30)
            degree = -degree;

        if (x<0 || x>500-30)
            degree = Math.PI - degree;

        if (speed>0){
            speed -= 0.05;
        } else {
            speed =0;
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
