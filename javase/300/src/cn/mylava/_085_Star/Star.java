package cn.mylava._085_Star;

import cn.mylava._085_Star.util.GameUtil;

import java.awt.*;

/**
 * Created by lpf on 16/8/5.
 */
public class Star {
    Image img;
    double x,y;
    int width,height;

    public Star() {
    }

    public void draw(Graphics g){
        g.drawImage(img,(int)x,(int)y,null);
    }

    public Star(Image img){
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public Star(Image img, double x, double y) {
        this(img);
        this.x = x;
        this.y = y;
    }

    public Star(String path, double x, double y) {
        this(GameUtil.getImage(path),x,y);
    }
}
