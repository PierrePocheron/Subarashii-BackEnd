package com.jfam.subarashii.configs;

import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service
public class HttpClient {

    public <T> T PostQuery(String url, Object body){
        return (T) Unirest.post(url).body(body).asJson().getBody();
    }
    public <T> T GetQuery(String url){
        return (T) Unirest.get(url).asJson().getBody();
    }

}
