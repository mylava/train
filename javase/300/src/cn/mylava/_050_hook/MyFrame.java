package cn.mylava._050_hook;

/**
 * 16/3/14.
 */
public class MyFrame {
    public void paint() {
        System.out.println("MyFrame.paint()");
    }
}

class GameFrame extends MyFrame{
    @Override
    public void paint() {
        System.out.println("GameFrame.paint()");
    }
}