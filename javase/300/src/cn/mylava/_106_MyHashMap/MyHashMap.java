package cn.mylava._106_MyHashMap;

import java.util.LinkedList;


/**
 * hashMap是基于数组+链表结构实现的
 *
 * 知识点:根据hashcode取余某个数n,得到的结果都在0到n-1范围内
 */
public class MyHashMap {

    private LinkedList[] arr = new LinkedList[999];
    int size;

    public void put(Object key,Object value){
        Entry e =  new Entry(key,value);

        //hashcode可能为负数,对于负数进行处理
        int hash = key.hashCode();
        hash = key.hashCode()<0?-hash:hash;


        int a = hash%arr.length;
        if (arr[a]==null){
            LinkedList list = new LinkedList();
            list.add(e);
            arr[a] = list;
        } else {
            /**
             * 键重复,直接覆盖
             */
            for (Object obj : arr[a]) {
                Entry entry = (Entry) obj;
                if (entry.key.equals(key)){
                    entry.value = value;
                    return;
                }
            }
            /**
             * 键不重复
             */
            arr[a].add(e);
        }
        size++;
    }

    public Object get(Object key){
        //hashcode可能为负数,对于负数进行处理
        int hash = key.hashCode();
        hash = key.hashCode()<0?-hash:hash;
        int a = hash%arr.length;
        if (arr[a]!=null){
            for (Object obj : arr[a]) {
                Entry entry = (Entry) obj;
                if (entry.key.equals(key)){
                    return entry.value;
                }
            }
        }

        return null;
    }


    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put("张三","张三丰");
        map.put("李四","李四光");
        map.put("张三", "张三疯");
        System.out.println(map.get("张三"));

    }

}
