package cn.mylava._048_abstract;

/**
 * 16/3/10.
 *
 * 抽象类
 */
public abstract class Animal {
    //抽象类的意义在于将方法的设计和实现分离,在抽象类中设计,在实现类中实现.
    public abstract void run();
    //非抽象方法调用抽象方法
    public void breath(){
        run();
    }
}

class Cat extends Animal{
    @Override
    public void run() {
        System.out.println("猫步");
    }
}
