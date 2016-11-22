package cn.mylava._036_Static;

/**
 * 16/3/9.
 *
 * static关键字
 */
public class Student {

    public static int ss;
    public static void printSS(){
        System.out.println(ss);
    }

    public static void main(String[] args) {
        /**
         * 静态变量/方法也叫类变量/方法,在内存中存在于对内存的方法区的类信息中.
         */
        Student.ss=23;
        Student.printSS();
    }
}
