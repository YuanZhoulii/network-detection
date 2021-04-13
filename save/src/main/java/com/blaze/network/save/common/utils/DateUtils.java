package com.blaze.network.save.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

/**
 * @author yuanzhouli
 * @date 2021/3/18 - 0:37
 */
public class DateUtils {
    public static Date StringToDate(String source){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.US);
        Date result=null;
        try {
            result = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            System.out.println(e);
        }finally {
            return result;
        }
    }

    public static void main(String[] args) {
        String str=null;
//        str.equals("");//空指针异常
        Optional.ofNullable(str).ifPresent(s->s.equals(""));
    }
}
