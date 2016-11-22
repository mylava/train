package cn.mylava._012_Variable;

/**
 * 16/2/18.
 * 变量
 */
public class TestVariable {

    public static void main(String[] args) {
        int a;
        a = 3;
        //不初始化会报错
        System.out.println(a);

//      常量只能赋值一次
        final int MAX = 12;
//      MAX=28;  这句报错,不能赋值
    }
}
