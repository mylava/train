package cn.mylava._112_ListMap;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lpf on 16/8/10.
 */
public class Test01 {
    public static void main(String[] args) throws ParseException {
        Employee e1 = new Employee(0301,"张三",3000,"研发部","2010-07");
        Employee e2 = new Employee(0302,"李四",3500,"研发部","2006-07");
        Employee e3 = new Employee(0303,"王五",3550,"研发部","2010-07");

        /**
         * List容器
         */
        List<Employee> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);

        printEmpName(list);

        /**
         * Map容器
         */
        Map map = new HashMap<>();
        map.put("id",0301);
        map.put("name", "张三");
        map.put("salary", 3000);
        map.put("department", "研发部");
        map.put("hireDate", "2007-10");

        Map map1 = new HashMap<>();
        map1.put("id",0302);
        map1.put("name","李四");
        map1.put("salary",3500);
        map1.put("department","研发部");
        map1.put("hireDate","2006-10");

        Map map2 = new HashMap<>();
        map2.put("id",0303);
        map2.put("name","王五");
        map2.put("salary",3550);
        map2.put("department","研发部");
        map2.put("hireDate","2006-10");

        List<Map> list1 = new ArrayList<>();
        list1.add(map);
        list1.add(map1);
        list1.add(map2);
        System.out.println("####################");
        printEmpName1(list1);
    }

    public static void printEmpName(List<Employee> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }

    public static void printEmpName1(List<Map> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get("name"));
        }
    }

}
