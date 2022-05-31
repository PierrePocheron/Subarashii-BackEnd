package com.jfam.subarashii.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jfam.subarashii.entities.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Helpers {

    /***
     * Get local date , add any minutes then convert to java.util.date
     * @return a current java.util.date plus x minutes
     */
    public static Date currentDatePlusMinutes(int minute)
    {
        Date date = new Date();
        // Convert Date to Calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static String substringBefore(String sentence, String word){
        return StringUtils.substringBefore(sentence, word);
    }

    public static List<String> getElementInListNotInMapParams(Map<String,String> mapParams, List<String>  listParamsAuthorize ){
        return mapParams.keySet().stream()
                .filter(element -> !listParamsAuthorize.contains(element))
                .collect(Collectors.toList());
    }

    public static Map<String, Object> convertJsonObjectToMap(JsonObject jsonObject){
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), Map.class);
    }

    /**
     * Créer à partir d'un map un string représentant les paramètres d'une query
     * @param allParams
     * @return
     */
    public static String getQueryFromMap(Map<String, String> allParams) {
        StringBuilder queryBuilder = new StringBuilder();
        allParams.forEach((k, v) -> {
            queryBuilder.append(String.format(Constantes.ApiMovie.OTHER_QUERY_PARAMS_SYNTAX, k, v));
        });
        return queryBuilder.toString();
    }

    /**
     *
     */
    public static User getCurrentUser(HttpServletRequest req){
        return (User) req.getAttribute(Constantes.Keys.USER);
    }



    public static String getDateNow() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

}
