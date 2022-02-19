package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void LoginUser(@RequestBody User user, HttpServletResponse response) throws IOException {
        userService.login(response,user);
        //response.setHeader("Authorization-bearer", "eyJyb2xlIjoiZGV2ZWxvcGVyIiwidHlwIjoiSldUIiwicHNldWRvIjoiZGV2ZWxvcGVyIiwiYWxnIjoiSFMyNTYiLCJlbWFpbCI6ImRldmVsb3BlckB0ZXN0LmZyIn0.eyJleHAiOjE2NDUxMjk3Mjl9.HQY4eAd-7lSraLcI18gJYvv83l8ujW_oIJ8kH39qbSU");
    }
}
