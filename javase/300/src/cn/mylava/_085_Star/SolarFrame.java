package cn.mylava._085_Star;


import cn.mylava._082_Game.BaseFrame;
import cn.mylava._082_Game.Constant;
import cn.mylava._085_Star.util.GameUtil;

import java.awt.*;

/**
 * Created by lpf on 16/8/5.
 */
public class SolarFrame extends BaseFrame {
    Image bg = GameUtil.getImage("images/bg.jpg");
    Star sun = new Star("images/sun.jpg", Constant.WIDTH/2,Constant.HEIGHT/2);
    Planet earth = new Planet("images/earth.jpg",150,100,0.1,sun);
    Planet mars = new Planet("images/Mars.jpg",200,130,0.2,sun);
    Planet moon = new Planet("images/moon.jpg",20,30,0.2,earth,true);

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg,0,0,null);
        sun.draw(g);
        earth.draw(g);
        mars.draw(g);
        moon.draw(g);
    }

    public static void main(String[] args) {
        new SolarFrame().launchFrame();

    }



}
