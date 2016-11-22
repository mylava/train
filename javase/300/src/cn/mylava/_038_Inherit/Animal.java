package cn.mylava._038_Inherit;

/**
 * 16/3/9.
 * 继承
 */

/**
 * 动物类
 */
public class Animal {
    String eye;
    public void run(){
        System.out.println("run-----------");
    }
    public void eat(){
        System.out.println("eat-----------");
    }
    public void sleep(){
        System.out.println("Zzzzzzzzz~");
    }

    public Animal() {
        //所有的构造器,第一句话都是super()
        //如果没有显式调用,则会被隐式调用
        super();
        System.out.println("创建一个动物");
    }
}

/**
 * 哺乳动物
 */
class Mammal extends Animal{
    public Mammal() {
        System.out.println("哺乳动物++++++++++++++");
    }
}

/**
 * 爬行动物
 */
class reptile extends Animal{
    public reptile() {
        System.out.println("爬行动物+++++++++++++");
    }
}

/**
 * 飞行动物
 */
class Birds extends Animal{
    public Birds() {
        //虽然没有显式调用super(),但是父类Animal的构造方法还是会被调用
        System.out.println("飞行动物++++++++++");
    }
    @Override
    public void run(){
        System.out.println("fly-------------");
    }

}