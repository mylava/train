package cn.mylava._025_Recursion;

/**
 * 16/2/24.
 *
 * 递归
 */
public class TestRecursion {

    static int a = 0;
    /**
     * 自己调自己,就是递归
     */
    public static void test01(){
        a++;
        System.out.println("test01----"+a);
        if(a<=10) {
            test01();
        }

    }

    /**
     * 通过递归实现阶乘
     * @param n
     * @return
     */
    public static long factorial(int n){
        if(n==1){
            return 1;
        }else {
            return n*factorial(n-1);
        }

    }

    public static void main(String[] args) {
//        test01();
        System.out.println(factorial(5));
    }

}
