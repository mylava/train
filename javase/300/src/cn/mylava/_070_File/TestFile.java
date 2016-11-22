package cn.mylava._070_File;

import java.io.File;
import java.io.IOException;

/**
 * 16/3/31.
 * 文件操作
 */
public class TestFile {
    public static void main(String[] args) {
        File f1 = new File("/test.txt");
        File f2 = new File("/test");
        File f3 = new File("/test","test.java");

        if(f1.isFile()){
            System.out.println("是一个文件");
        }
        if (f2.isDirectory()) {
            System.out.println("是一个目录");
        }
        File f4 = new File("/test","newFile.java");
        try {
            f4.createNewFile();
            f2.mkdir();
        } catch (IOException e) {
            e.printStackTrace();
        }
        f4.delete();
    }

}
