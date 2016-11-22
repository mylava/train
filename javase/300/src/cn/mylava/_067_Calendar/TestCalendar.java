package cn.mylava._067_Calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 16/3/31.
 * 测试日历类
 */
public class TestCalendar {
    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
        Calendar calendar = new GregorianCalendar();
//        calendar.set(2008, 2, 14);
        calendar.set(Calendar.YEAR, 2033);
        Date date = calendar.getTime();
        calendar.getTimeInMillis();

        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 30);
        System.out.println(calendar.getTime());


    }

}
