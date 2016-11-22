package cn.mylava._116_Generic.generic;

/**
 * 泛型类:声明时使用泛型
 * 字母:
 *          T Type 表示类型
 *          K V 分别代表兼职中的key value
 *          E 代表Element
 * 使用时确定类型
 * 注意:
 *  1.泛型只能使用引用类型,不能使用基本类型
 *  2.泛型声明时字母不能使用在静态属性|静态方法上.
 *
 */
public class Student<T> {
    private T javaScore;
    private T oracleScore;

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
        //使用时指定类型(引用类型)
        Student<Integer> student = new Student<>(90,100);
        student.setJavaScore(100);
        student.setOracleScore(80);

        int java = student.getJavaScore();


    }
}
