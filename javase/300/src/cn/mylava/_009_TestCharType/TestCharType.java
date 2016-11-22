package cn.mylava._009_TestCharType;

/**
 * 15/12/30.
 *
 * char和boolean类型
 */
public class TestCharType {
    public static void main(String[] args){
        char c1='a';
        char c2='飞'; //java使用unicode字符集，每个字符占2个字节 16位
        char c3='\''; //转义字符

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        int i=c1;
        System.out.println(i);



        boolean b=true;
        if(b){
            System.out.println(b);
        }

    }
}
