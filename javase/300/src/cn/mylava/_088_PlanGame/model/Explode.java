package cn.mylava._088_PlanGame.model;


import cn.mylava._088_PlanGame.util.GameUtil;

import java.awt.*;

/**
 * Created by lpf on 16/8/8.
 */
public class Explode {
    double x,y;
    //所有爆炸共用的图片,定义为static
    static Image[] imgs = new Image[16];
    int count;
    static {
        for (int i=0;i<16;i++){
            imgs[i] = GameUtil.getImage("images/explode/e"+(i+1)+".gif");
            imgs[i].getWidth(null);
        }
    }

    public void draw(Graphics g){
        if (count<=imgs.length - 1){
            g.drawImage(imgs[count],(int)x,(int)y,null);
            count++;
        }
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
