package cn.mylava._018_TestSwitch;

/**
 * 16/2/24.
 *
 * Switch语句
 */
public class TestSwitch {
    public static void main(String[] args) {

        char c = 'a';
        int rand = (int)(26*Math.random());
        char ch = (char)(c+rand);
        System.out.println("c2==="+ch);
        /**
         * switch(e)  其中e的数据类型必须是int或enum类型的变量,或者可以自动转换承int类型的变量(byte,char,short)
         */
        switch (ch){
            /**     巧妙利用case穿透     */
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                System.out.println("元音");
                break;
            case 'y':
            case 'w':
                System.out.println("半元音");
                break;
            default:
                System.out.println("辅音");
        }

        /**
         *  jdk1.7之后, 可以放置字符串类型的变量.
         */
        System.out.println("JDK1.7新特性*************************************");
        String str = "mylava";
        switch (str){
            case "mylava":
                System.out.println("输入mylava");
                break;
            case "sunlava":
                System.out.println("输入sunlava");
                break;
            default:
                System.out.println("输入java");
        }

    }

}
