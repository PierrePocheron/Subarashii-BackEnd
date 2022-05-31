package com.jfam.subarashii.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.api.ApiPaginationResults;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

/**
 * The client used is Unirest, you can find the documentation at : http://kong.github.io/unirest-java/
 */
@Service
public class HttpClient {
    Gson gson = new Gson();

    public JsonObject getQuery(String url) throws ResourceApiNotFoundException {
        LinkedTreeMap ltm = (LinkedTreeMap) Unirest.get(url)
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Authorization", Constantes.ApiMovie.TOKEN_SECRET)
                .asObject(Object.class)
                .getBody();

        JsonObject jsonObject = gson.toJsonTree(ltm).getAsJsonObject();
        if(jsonObject.has("status_code") && jsonObject.get("status_code").getAsInt() == 34){
           throw new ResourceApiNotFoundException(Constantes.ErrorMessage.RESOURCE_NOT_FOUND);
        }
        return gson.toJsonTree(ltm).getAsJsonObject();
    }

    public ApiPaginationResults getQueryPageableResult(String url) throws ResourceApiNotFoundException {
        LinkedTreeMap ltm = (LinkedTreeMap) Unirest.get(url)
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Authorization", Constantes.ApiMovie.TOKEN_SECRET)
                .asObject(Object.class)
                .getBody();

        JsonObject jsonObject = gson.toJsonTree(ltm).getAsJsonObject();
        if(jsonObject.has("status_code") && jsonObject.get("status_code").getAsInt() == 34){
           throw new ResourceApiNotFoundException(Constantes.ErrorMessage.RESOURCE_NOT_FOUND);
        }
        JsonObject jsonResult =  gson.toJsonTree(ltm).getAsJsonObject();
        Gson gson = new Gson();
        return gson.fromJson(jsonResult.toString(), ApiPaginationResults.class);
    }




}
