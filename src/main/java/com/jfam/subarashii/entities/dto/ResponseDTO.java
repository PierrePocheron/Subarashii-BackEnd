package com.jfam.subarashii.entities.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
    private String message;
    private int status;
    private Object body;

    public ResponseDTO(String message, int status, Object body) {
        this.message = message;
        this.status = status;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

}
