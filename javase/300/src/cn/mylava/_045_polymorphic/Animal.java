package cn.mylava._045_polymorphic;

/**
 * 16/3/10.
 * 多态
 */
public class Animal {
    String string;
    public void voice(){
        System.out.println("普通动物叫声");
    }
}

class Cat extends Animal{
    @Override
    public void voice() {
        System.out.println("喵喵喵");
    }
    public void catchMouse(){
        System.out.println("抓老鼠");
    }
}

class Dog extends Animal{
    @Override
    public void voice() {
        System.out.println("汪汪汪");
    }
}

class Pig extends Animal{
    @Override
    public void voice() {
        System.out.println("哼哼哼");
    }
}
