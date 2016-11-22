package cn.mylava._050_hook;

/**
 * 16/3/14.
 * 回调函数   钩子
 */
public class Run {
    public static void drawFrame(MyFrame frame){
        System.out.println("前期工作");
        //调用其他地方的具体实现--具体的实现放到其他类中封装
        frame.paint();
        System.out.println("后期工作");
    }
    public static void main(String[] args) {
        drawFrame(new GameFrame());
    }
}

