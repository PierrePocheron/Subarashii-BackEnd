package com.jfam.subarashii.utils;

import com.google.gson.JsonArray;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public static String SubstringBefore(String sentence,String word){
        return StringUtils.substringBefore(sentence, word);
    }

    public static  <T> List<T> JsonArrayToList(JsonArray jsonArray) {
        List<T> list = new ArrayList<T>();
        for (int i=0; i<jsonArray.size(); i++) {
            list.add((T)jsonArray.get(i));
        }
        return list;
    }
}
