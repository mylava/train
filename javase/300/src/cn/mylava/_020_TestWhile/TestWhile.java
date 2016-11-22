package cn.mylava._020_TestWhile;

/**
 * 16/2/24.
 *
 * While循环
 */
public class TestWhile {
    public static void main(String[] args) {
        int a = 1;      //初始化
        int sum = 0;
        while (a<=100){     //条件判断

            sum += a;//循环体

            a++;    //迭代
        }
        System.out.println(sum);
    }


}
