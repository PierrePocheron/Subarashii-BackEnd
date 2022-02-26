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
        return (T) Unirest.post(url)
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Authorization", Constantes.ApiMovie.TOKEN_SECRET)
                .body(body).asJson().getBody();
    }

    public <T> T GetQuery(String url){
        LinkedTreeMap LTM = (LinkedTreeMap) Unirest.get(url)
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Authorization", Constantes.ApiMovie.TOKEN_SECRET)
                .asObject(Object.class)
                .getBody();
/*        LTM = removeDataTag(LTM);*/
        return (T) gson.toJsonTree(LTM).getAsJsonObject();
    }

    LinkedTreeMap removeDataTag(LinkedTreeMap LTM){
        var key = LTM.keySet().iterator().next();
        return (LinkedTreeMap) LTM.get(key);
    }
}
