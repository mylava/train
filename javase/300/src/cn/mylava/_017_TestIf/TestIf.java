package cn.mylava._017_TestIf;

/**
 * 16/2/23.
 *
 * If语句
 */
public class TestIf {
    public static void main(String[] args) {
        double d = Math.random();
        int e = (int)(d*5); //[0,4]

        if(e>3){
            System.out.println("大数--------------"+e);
        }else {
            System.out.println(e);
        }


        int x = 1+(int)(d*6);//模拟掷骰子
        if(x==6){
            System.out.println("运气非常好");
        }else if(x==5){
            System.out.println("运气不错");
        }else if(x==4){
            System.out.println("运气一般吧");
        }else if(x==3){
            System.out.println("运气有点差");
        }

    }
}
