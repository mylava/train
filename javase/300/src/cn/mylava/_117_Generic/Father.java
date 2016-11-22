package cn.mylava._117_Generic;

/**
 * Created by lpf on 16/8/10.
 *
 * 自定义泛型的使用
 *
 * 父类为泛型类
 * 1 属性
 * 2 方法
 *
 * 泛型类的子类声明的泛型范围要么大于等于父类(Child2),要么就同时擦除(Child4)
 *
 * 1 属性类型
 * 父类中,随父类而定
 * 子类中,随子类而定
 * 2.方法重写
 * 随父类而定
 */
public abstract class Father<T,T1> {

    T name;
    public abstract void test(T t);

}

/**
 * 子类声明时指定具体类型
 * 属性类型也变成具体类型
 * 方法同理
 */
class Child1 extends Father<String,Integer>{
    @Override
    public void test(String s) {
        //name也是String类型
        String aaa = this.name;
    }
}

/**
 * 子类也是泛型类,类型在使用的时候确定
 * @param <T1>
 * @param <T>
 */
class Child2<T1,T,T2> extends Father<T1,T> {
    @Override
    public void test(T1 t1) {

    }
}

/**
 * 子类为泛型类,父类不指定类型,泛型的擦除,使用Object替换
 */
class Child3<T1,T2> extends Father {
    /**
     * 注意这里参数的类型变成了Object类型
     * @param o
     */
    @Override
    public void test(Object o) {
        Object name = this.name;
    }
}

/**
 * 子类与父类同时擦书
 */
class Child4 extends Father{
    @Override
    public void test(Object o) {

    }
}

