package cn.mylava._114_Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lpf on 16/8/10.
 */
public class TestIterator {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        Iterator it = list.iterator();
        while (it.hasNext()){
            Object obj = it.next();
            System.out.println(obj);
            it.remove();
        }
        System.out.println(list.size());
    }

}
