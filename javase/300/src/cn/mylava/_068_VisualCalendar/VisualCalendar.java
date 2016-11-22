package cn.mylava._068_VisualCalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *  16/3/31.
 *  可视化日历
 */
public class VisualCalendar {
    public static void main(String[] args) throws ParseException {
        System.out.println("请输入日期:如2016-03-31");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dataFormat.parse(temp);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        //获取当月第一天是星期几
        calendar.set(Calendar.DATE, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        //一个月有多少天
        int maximum = calendar.getActualMaximum(Calendar.DATE);
        System.out.println("日\t一\t二\t三\t四\t五\t六");
        for(int i=1;i<=maximum;i++){
            if(i==1){
                for(int j=firstDayOfWeek;j>1;j--){
                    System.out.print("\t");
                }
            }
            if(i==day){
                System.out.print("*");
            }
            System.out.print(i + "\t");
            if ((i+firstDayOfWeek-1)%7 == 0) {
                System.out.println();
            }
        }
    }
}
