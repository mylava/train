package cn.mylava._059_ArrayList;

/**
 * 16/3/15.
 */
public class Run {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList(2);
        list.add("aaa");
        list.add(new Item("张三"));
        list.add("bbb");
        System.out.println(list.getSize());
        System.out.println(list.isEmpty());
        System.out.println(list.set(2, "222"));
        System.out.println(list.get(2));
    }
}
