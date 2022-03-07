package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import com.jfam.subarashii.entities.dto.UserListAnimeDTO;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.UserListService;
import com.jfam.subarashii.services.UserService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "user-list")
public class UserListController {

    @Autowired
    UserListService userListService;

    @Autowired
    ResponseService responseService;

    @Autowired
    AnimeService animeService;


    @Operation(summary = Constantes.Swagger.SUMMARY_USER_LIST_GET_MY_LIST)
    @GetMapping("/mylist")
    public void getCurrentUserList(HttpServletRequest req,HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute(Constantes.Keys.USER);
        List<UserList> listUserLists=  userListService.getCurrentUserList(currentUser);
        responseService.SuccessF(res, Constantes.SuccessMessage.USER_LIST_GET_CURRENT_LIST,listUserLists);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_LIST_CREATE_LIST)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createCustomList(@RequestBody  UserList userList, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute(Constantes.Keys.USER);
        UserList customUserList =  userListService.createCustomList(currentUser,userList.getNom());
        responseService.SuccessF(res, Constantes.SuccessMessage.USER_LIST_CREATE_OK,customUserList);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_LIST_ADD_ANIME)
    @PutMapping("/addanime")
    public void addAnimeToUserList(@RequestBody UserListAnimeDTO userListAnimeDTO, HttpServletRequest req, HttpServletResponse res ) throws IOException, ResourceApiNotFoundException {
        User currentUser = (User) req.getAttribute(Constantes.Keys.USER);

        UserList theUserListCurrentUser = userListService.getOneUserListByIdForCurrentUser(userListAnimeDTO.idUserList, currentUser);

        if(theUserListCurrentUser == null){
            responseService.ErrorF(res,Constantes.ErrorMessage.ERROR_ADD_ANIME_TO_USER_LIST,HttpServletResponse.SC_NOT_FOUND,false);
            return;
        }

        Anime animeToAdd =  animeService.getByIdApi(userListAnimeDTO.idApiAnime);
        List<Anime> animeList= theUserListCurrentUser.getAnimes(); // récupération les animés dans la liste de l'utilisateur
        if (animeList.contains(animeToAdd))
        {
            responseService.ErrorF(res,Constantes.ErrorMessage.ERROR_ADD_ANIME_ALSO_EXIST,404,false);
            return;
        }
        UserList ul = userListService.addAnimeToUserList(animeToAdd, animeList,theUserListCurrentUser);
        responseService.SuccessF(res, String.format(Constantes.SuccessMessage.ADD_ANIME_ON_USER_LIST, animeToAdd.getNom(), theUserListCurrentUser.getNom()),ul);
    }
}
