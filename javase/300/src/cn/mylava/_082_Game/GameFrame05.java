package cn.mylava._082_Game;

import java.awt.*;

/**
 * Created by lpf on 16/8/5.
 */
public class GameFrame05 extends BaseFrame {
    /**物体的坐标*/
    private double x=100,y=100;
    private double degree = 3.14/3;
    private double speed = 10;

    Image img = GameUtil.getImage("images/sun.jpg");


    public static void main(String[] args) {
        GameFrame05 gf = new GameFrame05();
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
}
