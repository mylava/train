package cn.mylava.hadoop.sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by mylava on 2016/11/22.
 *
 * 实现可写（序列化）和 可比较 接口
 * set方法代替构造方法，节省mapper和reducer的内存
 *
 * Hadoop中，定义排序规则后将对象当做key，hadoop会自动根据Key进行排序
 *
 */
public class InfoBean implements WritableComparable<InfoBean> {
    //账号
    private String account;
    //收入
    private double income;
    //支出
    private double expenses;
    //结余
    private double surplus;

    public void  set(String account, double income, double expenses) {
        this.account = account;
        this.income = income;
        this.expenses = expenses;
        this.surplus = income-expenses;
    }

    public int compareTo(InfoBean o) {
        if (this.income == o.getIncome()) {
            return this.expenses > o.getExpenses() ? 1 : -1;
        } else {
            return this.income > o.income ? -1 : 1;
        }
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(account);
        out.writeDouble(income);
        out.writeDouble(expenses);
        out.writeDouble(surplus);
    }

    public void readFields(DataInput in) throws IOException {
        this.account = in.readUTF();
        this.income = in.readDouble();
        this.expenses = in.readDouble();
        this.surplus = in.readDouble();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getSurplus() {
        return surplus;
    }

    public void setSurplus(double surplus) {
        this.surplus = surplus;
    }

    @Override
    public String toString() {
        return this.income+"\t"+this.expenses+"\t"+this.surplus;
    }
}
