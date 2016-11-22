package cn.mylava._115_IteratorMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 两种遍历Map的方式
 */
public class IteratorMap {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",0301);
        map.put("name", "张三");
        map.put("salary", 3000);
        map.put("department", "研发部");
        map.put("hireDate", "2007-10");


        /**
         * 第一种,通过keyset遍历
         */
        Set<String> keys = map.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext();){
            String key = it.next();
            System.out.println(key+"======"+map.get(key));
        }
        System.out.println("###################");
        /**
         * 第二种,通过entrySet遍历
         */
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Iterator<Map.Entry<String, Object>> it = set.iterator();it.hasNext();) {
            Map.Entry<String, Object> entry = it.next();
            System.out.println(entry.getKey()+"========"+entry.getValue());
        }



    }
}
