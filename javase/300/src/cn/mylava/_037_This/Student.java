package cn.mylava._037_This;

/**
 * 16/3/9.
 *
 * this关键字
 */
public class Student {
    //静态数据
    private String name;
    private int id;

    public Student() {
        System.out.println("调用默认构造器");
    }

    public Student(int id) {
        //通过this方法调用其他构造方法,this()语句只能放在第一句
        this();
        this.id = id;
    }

    /**
     * 所有的方法中都有两个隐式参数,this和super
     *
     * 构造方法中,this指向正要初始化的对象
     */
    public Student(String name, int id) {
        //通过this方法调用构造器
        this(12);

        //如果没有使用this关键字,=左边的name采取就近原则,取的是行参的name,=右边的也是行参的name,这样name没有赋值
        name = name;
        System.out.println("不使用this关键字============");
        System.out.println("这是对象的属性:"+this.name);
        System.out.println("这是行参的属性:"+name);
        this.name = name;
        System.out.println("使用this关键字============");
        System.out.println("这是对象的属性:"+this.name);
        System.out.println("这是行参的属性:"+name);
    }

    /**
     * 普通方法中,this指向调用该方法的对象
     */
    public void study(){
        this.name = "张三";
        System.out.println(name+"在学习");
    }

    public void sayHello(String sname){
        System.out.println(name+"向"+sname+"说:你好!");
    }

    public static void main(String[] args) {
        Student s = new Student("张三",12);
        //如果构造方法中没有使用this关键字,此处打印为null
        System.out.println(s.name);
    }
}
