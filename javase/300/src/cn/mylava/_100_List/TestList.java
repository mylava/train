package cn.mylava._100_List;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class TestList {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        //ArrayList:底层实现是数组,查询快,修改 插入 删除慢.
        //LinkedList:底层实现是链表,查询慢,修改 插入 删除快.
        //Vector:线程安全的.
        list.add("aaa");
        list.add(new Date());
        list.add(1234);


        List list2 = new ArrayList<>();
        list2.add("bbb");
        list2.add("ccc");

        list.add(list2);

        System.out.println(list.size());

        list.set(1, "abab");
        list.remove(0);

        System.out.println(list.get(1));
    }
}
