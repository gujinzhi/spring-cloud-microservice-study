package com.daqsoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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



        System.out.println( DateUtil.getNextm("2019013124",1,"h","yyyyMMddHH"));
    }
}
