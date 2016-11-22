package cn.mylava._047_polymorphic;

/**
 *  16/3/10.
 *  多态-----模拟Servlet中方法的调用
 */
public class HttpServlet {
    public void service(){
        System.out.println("HttpServlet.service()");
        doGet();
    }
    public void doGet(){
        System.out.println("HttpServlet.doGet()");
    }


    public static void main(String[] args) {
        HttpServlet s = new MyServlet();
        /** 因为s没有service方法,所以会调用super.service();
         *  service()方法中调用doGet(),实际上是this.doGet()
         *  this指向当前对象,当前对象是s,所以调用s中的doGet()
         */
        s.service();
    }
}

class MyServlet extends HttpServlet{
    @Override
    public void doGet() {
        System.out.println("MyServlet.doGet()");
    }
}

