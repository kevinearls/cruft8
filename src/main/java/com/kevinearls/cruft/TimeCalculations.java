package com.kevinearls.cruft;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 1/24/13
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class TimeCalculations{


    public void fud(Calendar cal) {
        int minutes = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println(String.format("%02d:%02d", hour, minutes));
    }

    public static void main(String[] args) {
        TimeCalculations tc = new TimeCalculations();
        Calendar current = Calendar.getInstance();
        tc.fud(current);

        current.add(Calendar.MINUTE, 1);
        tc.fud(current);

        current.set(Calendar.MINUTE, 59);
        tc.fud(current);

        current.add(Calendar.MINUTE, 1);
        tc.fud(current);
    }
}
