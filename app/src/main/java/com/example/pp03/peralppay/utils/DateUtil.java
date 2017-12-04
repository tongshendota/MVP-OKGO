package com.example.pp03.peralppay.utils;

/**
 * Created by meixx on 2017/3/31.
 */

import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


/**
包括获取 1 当前年月日 2 当前是周几  3、根据日期获取是周几 4、获取7天的日期 5、获取当天往后的一周
 */
public class DateUtil {


    private static String mYear; // 当前年
    private static String mMonth; // 月
    private static String mDay;
    private static String mWay;
    private static String time;


    /**
     * 获取当前日期几月几号
     */
    public static String getDateString() {


        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if(Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear),(Integer.parseInt(mMonth)))){
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear),(Integer.parseInt(mMonth))));
        }
        return mMonth + "月" + mDay + "日";
    }

    /**
     * 获取当前时间
     */
      public static int  getTime(){
          Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
          t.setToNow(); // 取得系统时间。
          int hour = t.hour; // 0-23
          return hour;
      }
    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String StringData() {


        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if(Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear),(Integer.parseInt(mMonth)))){
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear),(Integer.parseInt(mMonth))));
        }
        return mYear + "-" + (mMonth.length()==1?"0"+mMonth:mMonth) + "-" + (mDay.length()==1?"0"+mDay:mDay);
    }


    /**
     * 根据当前日期获得是星期几
     *
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i =c.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                Week += "星期日";
                break;
            case 2:
                Week += "星期一";
                break;
            case 3:
                Week += "星期二";
                break;
            case 4:
                Week += "星期三";
                break;
            case 5:
                Week += "星期四";
                break;
            case 6:
                Week += "星期五";
                break;
            case 7:
                Week += "星期六";
                break;
            default:
                Week += "";
                break;
        }
        return Week;
    }


    /**
     * 获取今天之前一周的日期（几月几号）
     */
    public static List<DateBean> getSevendate() {
        List<DateBean> dates = new ArrayList<DateBean>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        for (int i = 0; i < 30; i++) {
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) - i);// 获取当前日份的日期号码
            if(Integer.parseInt(mDay) <= 0){
                int tempMonth = c.get(Calendar.MONTH);
                if (tempMonth==0){
                    mYear = String.valueOf(c.get(Calendar.YEAR)-1);
                    tempMonth = 12;
                }
                mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear),tempMonth)+(c.get(Calendar.DAY_OF_MONTH) - i));
                mMonth = String.valueOf(tempMonth);
            }
            if (mMonth.length()<2){
                mMonth = "0"+mMonth;
            }
            if (mDay.length()<2){
                mDay = "0"+mDay;
            }
            String formateDate = getWeek(mYear+"-"+mMonth+"-"+mDay);
            formateDate =mYear+"年"+ mMonth+"月"+mDay+"日  "+formateDate;
            DateBean bean = new DateBean(mYear+"-"+mMonth+"-"+mDay,formateDate);
            dates.add(bean);
        }
        return dates;
    }

    /**
     * 获取今天往后一周的集合
     */
    public static List<String> get7week() {
        String week = "";
        List<String> weeksList = new ArrayList<String>();
        List<String> dateList = get7date();
        for (String s : dateList) {
            if (s.equals(StringData())) {
                week = "今天";
            } else {
                week = getWeek(s);
            }
            weeksList.add(week);
        }
        return weeksList;
    }


    public static List<String> get7date() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        String date = sim.format(c.getTime());
        dates.add(date);
        for (int i = 0; i < 6; i++) {
            c.add(java.util.Calendar.DAY_OF_MONTH, 1);
            date = sim.format(c.getTime());
            dates.add(date);
        }
        return dates;
    }

    /**得到当年当月的最大日期**/
    public static int MaxDayFromDay_OF_MONTH(int year,int month){
        Calendar time= Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR,year);
        time.set(Calendar.MONTH,month-1);//注意,Calendar对象默认一月为0
        int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }
}