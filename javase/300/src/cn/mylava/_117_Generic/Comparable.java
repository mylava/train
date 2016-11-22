package cn.mylava._117_Generic;

/**
 * Created by lpf on 16/8/11.
 */
public interface Comparable<T> {
    void compare(T t);
}

//声明子类指定具体类型
class Comp implements Comparable<Integer> {
    @Override
    public void compare(Integer integer) {
    }
}

//同时擦除
class Comp1 implements Comparable{
    @Override
    public void compare(Object o) {
    }
}

//父类擦除,子类泛型
class Comp2<T> implements Comparable{
    @Override
    public void compare(Object o) {
    }
}

//子类泛型>=父类泛型
class Comp3<T,T1> implements Comparable<T> {
    @Override
    public void compare(T t) {
    }
}
