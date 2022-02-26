package com.jfam.subarashii.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

/**
 * The client used is Unirest, you can find the documentation at : http://kong.github.io/unirest-java/
 */
@Service
public class HttpClient {
    Gson gson = new Gson();
    public <T> T PostQuery(String url, Object body){
        return (T) Unirest.post(url).body(body).asJson().getBody();
    }

    public <T> T GetQuery(String url){
        LinkedTreeMap LTM = (LinkedTreeMap) Unirest.get(url)
                .asObject(Object.class)
                .getBody();
        LTM = removeDataTag(LTM);
        return (T) gson.toJsonTree(LTM).getAsJsonObject();
    }

    LinkedTreeMap removeDataTag(LinkedTreeMap LTM){
        var key = LTM.keySet().iterator().next();
        return (LinkedTreeMap) LTM.get(key);
    }


    public static class Route{
        private static final String BASE_ADRESS = "https://api.jikan.moe/v4/";
        public static final String GET_ANIME_BY_ID = BASE_ADRESS + "anime/";
    }
}
