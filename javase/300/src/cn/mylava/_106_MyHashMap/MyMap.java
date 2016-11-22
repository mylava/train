package cn.mylava._106_MyHashMap;

/**
 * Created by lpf on 16/8/9.
 */
public class MyMap {
    Entry[] arr = new Entry[999];
    int size;

    public void put(Object key,Object value){
        Entry entry = new Entry(key,value);
        /**
         * 处理已经存在的key
         */
        for (int i=0;i<size;i++){
            if (arr[i].key.equals(key)) {
                arr[i].value = value;
                return;
            }
        }
        /**
         * 处理不存在的key
         */
        arr[size++] = entry;
    }

    public Object get(Object key){
        for (int i=0;i<size;i++){
            if (arr[i].key.equals(key)) {
                return arr[i].value;
            }
        }
        return null;
    }

    public boolean containsKey(Object key){
        for (int i=0;i<size;i++){
            if (arr[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        MyMap map = new MyMap();
        map.put("张三","张三丰");
        map.put("李四","李四光");
        System.out.println(map.get("张三"));

    }

}

class Entry{
    Object key;
    Object value;

    public Entry() {
    }

    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
