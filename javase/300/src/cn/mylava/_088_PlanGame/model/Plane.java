package cn.mylava._088_PlanGame.model;


import cn.mylava._088_PlanGame.util.GameUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by lpf on 16/8/7.
 */
public class Plane extends GameObject{
    public boolean left,up,right,down;
    private boolean live = true;

    public Plane(String imagePath, double x, double y) {
        this.img = GameUtil.getImage(imagePath);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (live) {
            g.drawImage(img, (int) x, (int) y, null);
            move();
        }
    }

    public Plane() {
    }


    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move() {
        if (left)
            x -= speed;
        if (right)
            x += speed;
        if (up)
            y -= speed;
        if (down)
            y += speed;
    }

    public void addDirect(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            default:
                break;
        }
    }

    public void reduceDirect(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            default:
                break;
        }
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

}
