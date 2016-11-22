package cn.mylava._030_OOD;

/**
 * 16/2/29.
 *
 * 面向对象
 */
public class Student {
    //静态数据
    private String name;
    private int id;
    private int age;
    private String gender;
    private int weight;

    //动态行为
    public void study(){
        System.out.println(name+"在学习");
    }

    public void sayHello(String sname){
        System.out.println(name+"向"+sname+"说:你好!");
    }

    public static void main(String[] args) {
        /**
         * 1. 顺序执行代码
         * 2. 执行代码 Student:     jvm会在内存中查找Student类,如果内存中没有则通过Class Loader加载Student类的字节码文件.
         *    加载完成之后,在堆内存的方法区中就有了Student类的信息.
         * 3. 执行代码 s1:      因为s1是局部变量,jvm在栈内存中添加一个变量s1
         * 4. 执行代码 = new Student():       调用Student类的构造器,以Student类为模板,创建一个Student类的对象,之后将
         *    该对象的引用赋值给s1;
         *
         */
        Student s1 = new Student();//刚new出来的对象,所有的属性都没有赋值,所有属性都是默认值

        s1.name = "张三";
        s1.sayHello("张三丰");
    }
}
