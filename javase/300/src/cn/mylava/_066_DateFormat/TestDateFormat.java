package cn.mylava._066_DateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 16/3/31.
 * 时间格式化
 */
public class TestDateFormat {

    public static void main(String[] args) throws ParseException {

        DateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日");
        String str1 = df1.format(new Date());
        System.out.println(str1);

        DateFormat df2 = new SimpleDateFormat("yyyy,MM,dd");
        String str2 = "2016,3,31";
        Date date = df2.parse(str2);
        System.out.println(date);
    }


}