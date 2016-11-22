package cn.mylava._040_ToString;

/**
 * 16/3/9.
 *
 * 重写toString方法
 */
public class TestObject {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(obj.toString());
        Object object = new Object();
        System.out.println(object.toString());


        Mobile m = new Mobile();
        System.out.println(m.toString());
    }
}

class Mobile {
    @Override
    public String toString(){
        return "我是一部移动电话";
    }
}