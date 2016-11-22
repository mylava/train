package cn.mylava._112_ListMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lpf on 16/8/10.
 */
public class Employee {
    private int id;
    private String name;
    private int salary;
    private String department;
    private Date hireDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Employee(int id, String name, int salary, String department, String hireDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        try {
            this.hireDate = new SimpleDateFormat("yyyy-MM").parse(hireDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
