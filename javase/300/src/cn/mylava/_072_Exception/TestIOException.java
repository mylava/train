package cn.mylava._072_Exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 异常类之间如果有继承关系,需要将父类放在后边,越是顶层的类越往后放.
 */
public class TestIOException {
    public static void main(String[] args) {
        FileReader reader = null;
        try {
            reader = new FileReader("Test.txt");
            char c = (char) reader.read();
            System.out.println(c);
        }  catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (reader!=null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
