package com.example.hicall.utils;


import java.text.SimpleDateFormat;
import java.util.Date;


public class HiCallUitls {
    public static String getDateString(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }
}
