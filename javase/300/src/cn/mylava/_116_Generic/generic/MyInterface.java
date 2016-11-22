package cn.mylava._116_Generic.generic;

/**
 * Created by lpf on 16/8/10.
 * 接口中泛型字母只能使用在方法中,不能使用在常量中,因为接口中的常量默认就是static的
 */
public interface MyInterface<T,R> {
    R test(T t);
}
