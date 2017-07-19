package cn.mylava;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by lipengfei on 2017/6/5.
 */
public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:beans.xml" });
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
    }
}
