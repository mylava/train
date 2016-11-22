package cn.mylava._077_Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by lpf on 16/8/5.
 */
public class GameUtil {
    /** 构造方法私有化,只能使用静态方法 */
    private GameUtil() {
    }

    public static Image getImage(String path) {
        URL url = GameUtil.class.getClassLoader().getResource(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
