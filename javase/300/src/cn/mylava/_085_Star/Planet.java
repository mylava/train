package cn.mylava._085_Star;


import cn.mylava._085_Star.util.GameUtil;

import java.awt.*;

/**
 * Created by lpf on 16/8/7.
 */
public class Planet extends Star {
    private double longAxis;
    private double shortAxis;
    private double speed;
    private double degree;
    private Star center;
    private boolean satellite;


    public Planet(Image img, double x, double y) {
        super(img, x, y);
    }
    public Planet(String path, double x, double y) {
        super(path, x, y);
    }

    public Planet(String imgPath, double longAxis, double shortAxis, double speed, Star center) {
        super(GameUtil.getImage(imgPath));
        this.center = center;
        this.x = center.x + longAxis;
        this.y = center.y;

        this.longAxis = longAxis;
        this.shortAxis = shortAxis;
        this.speed = speed;
    }

    public Planet(String imgPath, double longAxis, double shortAxis, double speed, Star center, boolean satellite) {
        this(imgPath,longAxis,shortAxis,speed,center);
        this.satellite = satellite;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        move();
        if (!satellite) {
            drawTrace(g);
        }
    }

    public void move(){
        x=center.x+ center.width/2 +longAxis*Math.cos(degree);
        y=center.y+ center.height/2 +shortAxis*Math.sin(degree);

        degree += speed;
    }

    public void drawTrace(Graphics g){
        double ovalX,ovalY,ovalWidth,ovalHeight;

        ovalWidth = longAxis*2;
        ovalHeight = shortAxis*2;
        ovalX = center.x+center.width/2 - longAxis;
        ovalY = center.y+center.height/2 - shortAxis;
        Color color =  g.getColor();
        g.setColor(Color.blue);
        g.drawOval((int) ovalX, (int) ovalY, (int) ovalWidth, (int) ovalHeight);
        g.setColor(color);
    }

}
