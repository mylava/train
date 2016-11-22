package cn.mylava._117_Generic.mylava;

/**
 * 泛型的擦除
 *1.继承|实现|声明不指定类型
 * 2.使用时不指定类型,统一以Object对待
 */
public class Student<T> {
    private T javaScore;
    private T oracleScore;

    public Student() {
    }

    public Student(T javaScore, T oracleScore) {
        this.javaScore = javaScore;
        this.oracleScore = oracleScore;
    }

    public T getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(T javaScore) {
        this.javaScore = javaScore;
    }

    public T getOracleScore() {
        return oracleScore;
    }

    public void setOracleScore(T oracleScore) {
        this.oracleScore = oracleScore;
    }


    public static void main(String[] args) {
        Student stu = new Student();
        stu.setJavaScore("af");

    }
}
