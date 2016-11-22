package cn.mylava._008_TestFloatType;

/**
 * 15/12/30.
 * 测试浮点数
 */
public class TestFloatType {
    public static void main(String[] args){
        double d = 3.14;   //浮点类型常量默认为double类型
        float f = 6.28f;
        double d2 = 314e-2;
        System.out.println(d);
        System.out.println(f);
        System.out.println(d2);

        float f1 = 0.1f;
        double d1 = 1.0/10;
        System.out.println(f==d);
    }


}
