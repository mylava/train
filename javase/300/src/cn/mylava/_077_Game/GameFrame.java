package cn.mylava._077_Game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 基本窗口测试类
 * 游戏窗口类
 */
public class GameFrame extends Frame{
    private double x,y;
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
        GameFrame gf = new GameFrame();
        gf.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        g.drawLine(100, 100, 200, 200);
        g.drawRect(100, 100, 200, 200);
        g.drawOval(100, 100, 200, 200);
        Font font = new Font("宋体",Font.BOLD,50);
        g.setFont(font);
        g.drawString("我是测试", 200, 200);
        g.fillRect(100, 100, 20, 20);

        Color color = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillOval(300, 300, 20, 20);
        g.setColor(color);

        g.drawImage(img,(int)x,(int)y,null);
        x+=3;
        y+=3;
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




