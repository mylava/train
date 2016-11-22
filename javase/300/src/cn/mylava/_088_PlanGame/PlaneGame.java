package cn.mylava._088_PlanGame;


import cn.mylava._088_PlanGame.model.Bullet;
import cn.mylava._088_PlanGame.model.Explode;
import cn.mylava._088_PlanGame.model.Plane;
import cn.mylava._088_PlanGame.util.BaseFrame;
import cn.mylava._088_PlanGame.util.GameUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by lpf on 16/8/7.
 */
public class PlaneGame extends BaseFrame {
    Image bg = GameUtil.getImage("images/bg.jpg");
    Plane plane = new Plane("images/plane.png",50,50);
    ArrayList<Bullet> bulletList = new ArrayList<>();
    Explode explode ;

    public void paint(Graphics g){
        g.drawImage(bg, 0, 0, null);
        plane.draw(g);
        for (int i=0;i<bulletList.size();i++) {
            Bullet bullet = bulletList.get(i);
            bullet.draw(g);
            //子弹碰到飞机
            if (bullet.getRect().intersects(plane.getRect())) {
                System.out.println("碰撞");
                plane.setLive(false);
                Color color = g.getColor();
                g.setColor(Color.WHITE);
                g.drawString("OVER", 100, 200);
                g.setColor(color);
                if (explode == null)
                    explode = new Explode(plane.getX(),plane.getY());
                explode.draw(g);

                break;
            }
        }
    }


    public static void main(String[] args) {
        new PlaneGame().launchFrame();
    }

    public void launchFrame(){
        super.launchFrame();
        addKeyListener(new KeyMonitor());

        for (int i=0;i<50;i++){
            Bullet bullet = new Bullet();
            bulletList.add(bullet);
        }
    }
    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirect(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.reduceDirect(e);
        }
    }
}
