package com.jfam.subarashii.utils;

import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

/**
 * The client used is Unirest, you can find the documentation at : http://kong.github.io/unirest-java/
 */
@Service
public class HttpClient {

    public <T> T PostQuery(String url, Object body){
        return (T) Unirest.post(url).body(body).asJson().getBody();
    }

    public <T> T GetQuery(String url){
        return (T) Unirest.get(url).asJson().getBody();
    }

}
