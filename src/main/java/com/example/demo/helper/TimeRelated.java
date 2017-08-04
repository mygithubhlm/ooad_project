package com.example.demo.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by think on 2017/6/20.
 */
//a class used to complete some function
public class TimeRelated {
    public static long convertToDate(String time)throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");
            Date date = formatter.parse(time);
            return date.getTime();
    }
    public static boolean outOfTime(String ddl)throws Exception{
        long ddlT = convertToDate(ddl);
        return System.currentTimeMillis()>ddlT?true:false;
    }
    public static String convertToString(long time){
        SimpleDateFormat formatter =
                new SimpleDateFormat( "yyyy-MM-dd");
        String date = formatter.format(new Date(time));
        return date;
    }
}
