package cn.mylava._108_HashCode;

/**
 * Created by lpf on 16/8/9.
 * java中规定,如果两个对象equals为true,那么他们的hashcode也一定相等.
 */
public class Student {
    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        return !(name != null ? !name.equals(student.name) : student.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
