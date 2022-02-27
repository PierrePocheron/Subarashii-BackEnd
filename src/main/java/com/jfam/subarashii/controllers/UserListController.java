package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.UserListService;
import com.jfam.subarashii.services.UserService;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/userlists")
public class UserListController {

    @Autowired
    UserListService userListService;

    @Autowired
    ResponseService responseService;

    @GetMapping("/mylist")
    public void getCurrentUserList(HttpServletRequest req,HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute("user");
        List<UserList> listUserLists=  userListService.getCurrentUserList(currentUser);
        responseService.SuccessF(res, "La liste de l'utilisateur a correctement été récupéré",listUserLists);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createCustomList(@RequestBody  UserList userList, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute("user");
        UserList customUserList =  userListService.createCustomList(currentUser,userList.getNom());
        responseService.SuccessF(res, "La liste custom a correctement été crée",customUserList);
    }
}
