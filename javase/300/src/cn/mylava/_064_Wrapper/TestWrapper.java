package cn.mylava._064_Wrapper;

/**
 * 16/3/15.
 * 包装类   装箱  拆箱  缓存等
 */
public class TestWrapper {

    public static void main(String[] args) {
        /**
         * jdk1.5以后加入包装类型
         * 下边两种写法都不符合语法规范,但是编译器并不报错
         */
        int a1 = new Integer(100); //相当于new Integer(100).intValue()
        Integer a2 = 100;   //相当于 Integer.valueOf(100)
        /**
         * 思考以下对比,true还是false,为什么?
         */
        System.out.println("a1 对比 a2 \t" + (a1 == a2));

        Integer a3 = new Integer(100);
        Integer a4 = new Integer(100);
        System.out.println("a3 对比 a4 \t" + (a3 == a4));

        Integer a5 = 100;
        Integer a6 = 100;
        System.out.println("a5 对比 a6 \t" + (a5 == a6));

        Integer a7 = -129;  //不在 (-128,127)范围内的数字都可以
        Integer a8 = -129;
        System.out.println("a7 对比 a8 \t" + (a7 == a8));

        Integer a9 = 12;
        Integer a10 = new Integer(12);
        System.out.println("a9 对比 a10 \t" + (a9 == a10));

        int a11 = 12;
        Integer a12 = new Integer(12);
        System.out.println("a11 对比 a12 \t" + (a11 == a12));

        /**
         * 解释如下:
         *  1. a1对比a2  是int类型和Integer类型的对比,Integer会自动拆箱成int类型,所以就变成了值对比.
         *  2. a3对比a4  是Integer类型对象的对比,两个对象都是new出来的,是引用对比.
         *  3. a5对比a6  都是Integer.valueOf(100),JDK编写者为了提高运行效率设置了缓存对象,范围为(-128,127).缓存代码在下面.
         *  4. a7对比a8  解释同上.
         *  5. a9对比a10  a9为缓存中的对象,a10为new出来的对象,引用肯定不同.
         *  6. a11对比a12  解释同1
         */

        /** valueOf装箱代码
         * JVM在执行valueOf的时候，会先去检查内存中是否存在该数字，如果存在的话，就直接从内存中取出返回，如果不存在的话，就新建一个Integer对象。
            public static Integer valueOf(int i) {
                if (i >= IntegerCache.low && i <= IntegerCache.high)
                    return IntegerCache.cache[i + (-IntegerCache.low)];
                return new Integer(i);
            }
        */

    }


}
