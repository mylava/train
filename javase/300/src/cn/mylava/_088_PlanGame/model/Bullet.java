package cn.mylava._088_PlanGame.model;


import cn.mylava._088_PlanGame.util.Constant;

import java.awt.*;

/**
 * Created by lpf on 16/8/7.
 */
public class Bullet extends GameObject{
    double degree;

    public Bullet() {
        super();
        degree = Math.random()*Math.PI*2;
        x = Constant.WIDTH/2;
        y = Constant.HEIGHT/2;
        width = 3;
        height = 3;
        speed = 3;
    }

    public void draw(Graphics g){
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval((int) x, (int) y, width, height);
        g.setColor(color);
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);

        if (x<0||x>Constant.WIDTH-width)
            degree = Math.PI-degree;
        if (y<20||y>Constant.HEIGHT-height)
            degree = -degree;
    }

}
