package cn.mylava._023_BreakContinue;

/**
 * 16/2/24.
 *
 * Break 和 Continue
 */
public class TestBreakContinue {

    public static void main(String[] args) {
        int total = 0;
        System.out.println("Begin");
        while (true){
            total++;
            int i = (int)Math.round(100*Math.random()); //round()返回距离参数最近的long类型的数值,小数部分四舍五入
            if(i==88){
                break;
            }
        }
        System.out.println("Game over, used "+total+" times");


        System.out.println("***********\t 输出100~150之间不能被3整除的数\t****************");
        for(int i=100;i<=150;i++){
            if(i%3 == 0){
                continue;
            }
            System.out.print(i + "\t");
        }
        System.out.println();



        System.out.println("***********\t带标签的break或continue\t****************");
        int count = 0;
        outer:for(int i=101;i<150;i++){
            for(int j=2;j<i/2;j++){
                if(i%j==0){
                    continue outer;
                }
                System.out.print(i + "\t");
            }
            System.out.println();
        }

    }
}
