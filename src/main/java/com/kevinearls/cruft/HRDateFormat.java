package com.kevinearls.cruft;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 12/2/13
 * Time: 8:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class HRDateFormat {
    private static String exampleRunDate = "2013-09-07_00-07-19";
    private static final Locale currentLocale =  Locale.getDefault();


    /**
     *
     * @param unformattedDate   Looks like "2013-09-07_00-07-19";
     * @return Formatted date: "SEP 07"
     */
    public String getFormattedDate(String unformattedDate) {
        int firstDash = unformattedDate.indexOf("-");
        int secondDash = unformattedDate.indexOf("-", firstDash + 1);
        String month = unformattedDate.substring(firstDash + 1, firstDash + 3);
        String day = unformattedDate.substring(secondDash + 1, secondDash + 3);

        Calendar wtf = Calendar.getInstance();
        wtf.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        wtf.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

        SimpleDateFormat formatter = new SimpleDateFormat("MMM d", currentLocale);
        String result = formatter.format(wtf.getTime());
        return result;
    }

    public void go() {
        String result = getFormattedDate(exampleRunDate);
        System.out.println(result);
    }

    public static void main(String[] args) {
        HRDateFormat me = new HRDateFormat();
        me.go();
    }
}
