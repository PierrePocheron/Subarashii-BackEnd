package com.jfam.subarashii.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
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

    public JsonObject GetQuery(String url) throws ResourceApiNotFoundException {
        LinkedTreeMap LTM = (LinkedTreeMap) Unirest.get(url)
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Authorization", Constantes.ApiMovie.TOKEN_SECRET)
                .asObject(Object.class)
                .getBody();

        JsonObject jsonObject = gson.toJsonTree(LTM).getAsJsonObject();
        try{
            if(jsonObject.has("status_code") && jsonObject.get("status_code").getAsInt() == 34){
                throw new ResourceApiNotFoundException("la ressource que vous avez recherché n'a pas été trouvé");
            }
        }
        catch(ResourceApiNotFoundException exception){

        }
        if(jsonObject.has("status_code") && jsonObject.get("status_code").getAsInt() == 34){
           throw new ResourceApiNotFoundException("la ressource que vous avez recherché n'a pas été trouvé");
        }
        return gson.toJsonTree(LTM).getAsJsonObject();
    }
}
