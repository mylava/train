package cn.mylava._110_MyHashSet;

import java.util.HashMap;

/**
 * Created by lpf on 16/8/10.
 */
public class MyHashSet {
    HashMap map;
    private static final Object PRESENT = new Object();
    int size;

    public int size() {
        return size;
    }

    public MyHashSet() {
        map = new HashMap();
    }

    public void add(Object obj){
        map.put(obj, PRESENT);
        size = map.size();
    }

    public void remove(Object obj) {
        map.remove(obj);
        size = map.size();
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add("aaaa");
        System.out.println(set.size());
    }
}

