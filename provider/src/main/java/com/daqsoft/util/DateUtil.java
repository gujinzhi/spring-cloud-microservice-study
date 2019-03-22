package com.daqsoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    public static String getNextm(String date, int addm, String type, String  sd1) {
        SimpleDateFormat sd = new SimpleDateFormat(sd1);
        try {
            return getNextm(sd.parse(date),  addm,  type,   sd1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean compare(String time1, String sd1){
        SimpleDateFormat sd = new SimpleDateFormat(sd1);
       boolean  bo =false;

        try {
            Date timeCompare1 = sd.parse(time1);
            Date timeCompare2 =   sd.parse(sd.format(new Date()));
            if(timeCompare1.getTime() < timeCompare2.getTime()){
                bo = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return bo;

    }
    public static Map<String,Object> getdata(String time1, String sd1){
        Map<String,Object> ma = new HashMap<String,Object>();
        SimpleDateFormat sd = new SimpleDateFormat(sd1);
        String timeCompare1 =null;
        try {
            Date d = sd.parse(time1);

            Calendar calendar = Calendar.getInstance(); // 创建对象
            calendar.setTime(d);

            String m = (calendar.get(Calendar.MONTH ) + 1)+"";
            if(m.length() ==1){
                m = "0"+m;
            }
            ma.put("cname", calendar.get(Calendar.YEAR)+""+m);

            String day = calendar.get(Calendar.DAY_OF_MONTH )+"";
            if(day.length() ==1){
                day = "0"+day;
            }
            ma.put("dateNum", day);

            String h = calendar.get(Calendar.HOUR_OF_DAY) +"";
            if(h.length() ==1){
                h = "0"+h;
            }
            ma.put("hourtime", h);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ma;

    }
    public static String getNextm(Date date, int addm, String type, String  sd1) {
        SimpleDateFormat sd = new SimpleDateFormat(sd1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Long time = date.getTime();
        if("M".equals(type)) {
            cal.add(Calendar.MONTH, addm);
        }else if("h".equals(type)) {
            cal.add(Calendar.HOUR_OF_DAY, addm);
        }else if("d".equals(type)) {
            cal.add(Calendar.DATE, addm);
        }



        date = cal.getTime();
        return sd.format(date);


    }

    public static void  main(String[] s){



        System.out.println( DateUtil.getdata("20190101","yyyyMMdd").toString());
    }
}
