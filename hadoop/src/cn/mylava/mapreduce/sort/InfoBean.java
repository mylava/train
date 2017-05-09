package cn.mylava.mapreduce.sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by lipengfei on 2017/5/9.
 */
public class InfoBean implements WritableComparable<InfoBean>{
    //淘宝账号
    private String account;
    //收入
    private double income;
    //支出
    private double expenses;
    //结余
    private double surplus;

    @Override
    public int compareTo(InfoBean o) {
        if (this.surplus >= o.surplus) {
            return -1;
        } else {
            return 1;
        }
    }

    public void set(String account, double income, double expenses) {
        this.account = account;
        this.income = income;
        this.expenses = expenses;
        this.surplus = income-expenses;
    }

    @Override
    public String toString() {
        return income + "\t" + expenses + "\t" + surplus;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(account);
        out.writeDouble(income);
        out.writeDouble(expenses);
        out.writeDouble(surplus);
    }

    @Override
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
}
