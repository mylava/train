package cn.mylava._072_Exception;

/**
 * 16/4/13.
 * 异常的作用
 */
public class TestException {
    public static void main(String[] args) {
//        noCatch();
        withCatch();
    }

    /**
     * 有异常捕获和处理
     */
    public static void noCatch(){
        int a = 1/0;
        System.out.println("继续了=====");
    }

    /**
     * 没有异常捕获和处理
     */
    public static void withCatch(){
        try {
            int a = 1/0;
        }catch (Exception e) {
        }
        System.out.println("继续了=====");
    }

}
