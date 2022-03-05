package com.jfam.subarashii.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    public static List<String> GetElementInListNotInMapParams(Map<String,String> mapParams, List<String>  listParamsAuthorize ){
        return mapParams.keySet().stream()
                .filter(element -> !listParamsAuthorize.contains(element))
                .collect(Collectors.toList());
    }

    public static Map<String, Object> ConvertJsonElementToMap(JsonElement jsonElement){
        Gson gson = new Gson();
        return gson.fromJson(jsonElement.toString(), Map.class);
    }
}
