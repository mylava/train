package cn.mylava._021_For;

/**
 * 16/2/24.
 *
 * For循环
 */
public class TestFor {
    public static void main(String[] args) {
        int sum = 0;
        for(int i=1;i<=100;i++){//初表达式 布尔表达式  步进
            sum += i; //循环体
        }
        System.out.println(sum);

        /**
         * 九九乘法表
         */
        for(int i=1;i<10;i++){
            for (int j=1;j<=i;j++){
                System.out.print(j + "*" + i + "=" + i * j +"\t");
            }
            System.out.println("");
        }


    }
}
