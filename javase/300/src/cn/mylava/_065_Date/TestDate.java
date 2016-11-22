package cn.mylava._065_Date;

import java.text.ParseException;
import java.util.Date;

/**
 * 16/3/24.
 *
 * 时间类
 */
public class TestDate {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.currentTimeMillis();

        Date d2 = new Date(1462932166880l);
        System.out.println(d2.toGMTString());
        System.out.println(d2.toString());
    }


}
