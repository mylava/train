package cn.mylava._105_Map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lpf on 16/8/9.
 * Map的基本用法
 */
public class TestMap {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("张三",new Wife("张曼玉"));
        map.put("李四", new Wife("刘涛"));

        Wife wife = (Wife) map.get("张三");
        System.out.println(wife.name);
        map.remove("张三");
        System.out.println(map.containsKey("张三"));
    }
}

class Wife{
    String name;

    public Wife(String name) {
        this.name = name;
    }
}
