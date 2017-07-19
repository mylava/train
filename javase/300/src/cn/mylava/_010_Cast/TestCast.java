package cn.mylava._010_Cast;

/**
 * 15/12/30.
 *
 * 类型转换
 */
public class TestCast {

    public static void main(String[] args) {
        /**
         * 自动转型
         */
        byte b = 123; //int自动转byte
//      byte b2 = 128; //超过范围(-128,127)编译器报错
//      char c = -3;   //超过范围(0,65535)编译器报错
        char c2 = 'a';
        int i = c2; //自动转型
        long num = 123456789;
        float f = num; //long类型虽然在内存中占8位，但是范围小于float(占4位),可以自动转型，但是可能丢失精度


        /**
         * 强制转型
         */
        int i2 = -100;
        char c3 = (char) i2;
        int i3 = c3;
        System.out.println(c3); //超过char的表述范围，转换成一个无意义的值(打印出来的是一个乱码)


        /**
         * 类型自动提升
         *
         * 所有的二元运算符(+-/*%)都会有类型提升的问题
         */
        int a1 = 3;
        long a2 = 4;
//      int a3 = a1+a2; //编译不通过，两数相加后自动提升位long类型，超出了int的表述范围


        /**
         * 结果较大时，提前转型
         */
        long _times = 70*60*24*365*70;
        System.out.println(_times);

        long times = 70L*60*24*365*70;
        System.out.println(times);

    }


}
