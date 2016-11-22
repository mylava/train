package cn.mylava._116_Generic.common;

/**
 * Created by lpf on 16/8/10.
 */
public class Student {
    private Object javase;
    private Object Oracle;


    public Student() {
    }
    public Student(Object javase, Object oracle) {
        this.javase = javase;
        Oracle = oracle;
    }


    public Object getJavase() {
        return javase;
    }

    public void setJavase(Object javase) {
        this.javase = javase;
    }

    public Object getOracle() {
        return Oracle;
    }

    public void setOracle(Object oracle) {
        Oracle = oracle;
    }
}
