package com.jfam.subarashii.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

public class Helpers {

    /***
     * Get local date , add any minutes then convert to java.util.date
     * @return a current java.util.date plus x minutes
     */
    public static Date CurrentDatePlusMinutes(int minute)
    {
        Date date = new Date();
        // Convert Date to Calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }
}
