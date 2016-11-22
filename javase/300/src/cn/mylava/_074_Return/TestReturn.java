package cn.mylava._074_Return;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 测试return的执行顺序.
 * 有return值的执行顺序
 * 1.执行try catch,给返回值复制
 * 2.执行finally
 * 3.return
 */
public class TestReturn {
    public static void main(String[] args) {
        String str = new TestReturn().openFile();
        System.out.println(str);
    }

    String openFile(){

        try {
            System.out.println("aaa");
            FileInputStream fis = new FileInputStream("Test.txt");
            int a = fis.read();
            System.out.println("bbb");
            return "step1";
        } catch (FileNotFoundException e) {
            System.out.println("catching!!!!!");
            e.printStackTrace();
            return "step2"; //先确定返回的值,并不会直接结束运行
        } catch (IOException e) {
            e.printStackTrace();
            return "step3";
        }finally {
            System.out.println("finally!!!!!!");
            return "fff";   //不要在finally中使用return!
        }
    }
}
