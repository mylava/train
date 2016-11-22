package cn.mylava._035_Overload;

/**
 *  16/3/9.
 *
 *  重载  一个类有多个方法实现  超载
 */
public class TestOverload {
    public static void main(String[] args) {
        MyMath m = new MyMath();
        int result1 = m.add(4,5);
        System.out.println(result1);

        int result2 = m.add(4,5,6);
        System.out.println(result2);
    }

}

class MyMath{

    public int add(int a,int b){
        return a+b;
    }
    //参数类型不同
    public int add(double a,int b){
        return (int)a+b;
    }
    //参数顺序不同
    public int add(int a,double b){
        return a+(int)b;
    }
    //参数个数不同
    public int add(int a,int b,int c){
        return a+b+c;
    }
}
