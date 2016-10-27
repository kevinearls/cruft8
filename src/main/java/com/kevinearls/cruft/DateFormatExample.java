package com.kevinearls.cruft;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 2/18/13
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class DateFormatExample {
    public static void main(String[] args)  {
        Locale currentLocale =  Locale.getDefault();   // should be en_US
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMMdd", currentLocale);
        Date today = new Date();
        System.out.println(formatter.format(today));
    }
}
