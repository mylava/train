package cn.mylava.log.util;

import java.util.Random;

/**
 * Created by lipengfei on 2017/5/31.
 */
public class IdGenerator {
    public static String nextId() {
        return ""+new Random().nextLong();
    }
}
