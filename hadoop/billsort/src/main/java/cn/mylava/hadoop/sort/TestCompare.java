package cn.mylava.hadoop.sort;

/**
 * Created by mylava on 2016/11/23.
 */
public class TestCompare {
    public static void main(String[] args) {
        InfoBean bean1 = new InfoBean();
        InfoBean bean2 = new InfoBean();

        bean1.setIncome(1000);
        bean2.setIncome(2000);
        System.out.println(bean1.compareTo(bean2));
    }
}
