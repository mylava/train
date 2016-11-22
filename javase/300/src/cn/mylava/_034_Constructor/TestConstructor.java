package cn.mylava._034_Constructor;

/**
 * 16/3/9.
 *
 * 测试构造方法
 */
public class TestConstructor {
    private String name;

    public TestConstructor() {
        System.out.println("调用我了=======TestConstructor()");
    }

    public TestConstructor(String name) {
        this.name = name;
        System.out.println("调用我了=======TestConstructor(String name)");
    }

    public static void main(String[] args) {
        System.out.println("调用 无参 构造器***********************");
        TestConstructor t1 = new TestConstructor();

        System.out.println("调用 有参 构造器***********************");
        TestConstructor t2 = new TestConstructor("张三");
        System.out.println(t2.name);
    }

}