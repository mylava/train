package cn.mylava._043_Final;

/**
 * 16/3/9.
 * final关键字
 */

public final class Animal {
    String eye;
    //常量在内存中放置于常量池中,值不能被修改
    final String XXX="xxx";

    public final void run(){
//        XXX="aaa"; //final变量不能修改值.
        System.out.println("run-----------");
    }
    public void eat(){
        System.out.println("eat-----------");
    }
    public void sleep(){
        System.out.println("Zzzzzzzzz~");
    }
}

/*class Birds extends Animal{//final类不能被继承
    public Birds() {
        //虽然没有显式调用super(),但是父类Animal的构造方法还是会被调用
        System.out.println("飞行动物++++++++++");
    }
    @Override //final方法不能被重写
    public void run(){
        System.out.println("fly-------------");
    }

}*/
