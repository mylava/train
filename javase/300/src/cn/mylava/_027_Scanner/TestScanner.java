package cn.mylava._027_Scanner;

import java.util.Scanner;

/**
 * 16/2/25.
 *
 * 键盘输入
 */
public class TestScanner {
    static int i = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.next();//程序运行到next会阻塞,等待键盘的输入
        System.out.println("刚才输入的是:"+string);


        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }
}
