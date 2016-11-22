package cn.mylava._013_TestOperator;

/**
 * 16/2/23.
 *
 * 操作符
 */
public class TestOperator {
    public static void main(String[] args) {
        //赋值运算符 =
        int a = 3;

        //一元运算符 ++ --
        int b = a++;

        //逻辑运算符 && || !       -----------逻辑运算符需要注意短路问题
        boolean c = 1>2&&2>(3/0);    //是否会抛出运行是异常?
        System.out.println(c);

        //位运算符   ~   &   |    ^    <<    >>
        int m = 8;
        int n = 4;

        System.out.println("按位取反---------" + (~m)); // int类型的变量占4个字节  8的二进制表示形式位  1000_0000_00000000_00000000_0000_1000   其中第一位的表示符号,1为正,0为负
        System.out.println("按位与---------" + (m & n));
        System.out.println("按位或---------" + (m | n));
        System.out.println("按位异或---------" + (m ^ n));//异或的计算规则为   00或11得0   10或01得1    就是一样的都是0   不一样的得1
        System.out.println("按位左移---------" + (m << 1));
        System.out.println("按位右移---------" + (m >> 2));

        boolean bool = true & false;
        System.out.println("布尔类型的变量按位取与========="+bool); //布尔类型的变量在内存中占一位

        //扩展运算符  +=  -=  *=  /=  %=
        a += 5;
        System.out.println(a);


        //三元运算符

        String str = (a<b)?"a<b":"a>=b";
        System.out.println(str);

    }
}
