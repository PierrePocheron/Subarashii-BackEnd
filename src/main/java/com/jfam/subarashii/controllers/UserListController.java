package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.CustomErrorMessageException;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import com.jfam.subarashii.entities.dto.UserListAnimeDTO;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.UserListService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
    @GetMapping("/all")
    public void getCurrentUserList(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);
        List<UserList> listUserLists = userListService.getCurrentUserList(currentUser);
        responseService.successF(res, Constantes.SuccessMessage.USER_LIST_GET_CURRENT_LIST, listUserLists);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_LIST_CREATE_LIST)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createCustomList(@RequestBody UserList userList, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);
        UserList customUserList = userListService.createCustomList(currentUser, userList.getNom());
        responseService.successF(res, Constantes.SuccessMessage.USER_LIST_CREATE_OK, customUserList);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_LIST_ADD_ANIME)
    @PutMapping("/addanime")
    public void addAnimeToUserList(@RequestBody UserListAnimeDTO userListAnimeDTO, HttpServletRequest req, HttpServletResponse res) throws IOException, ResourceApiNotFoundException, ParseException {
        User currentUser = Helpers.getCurrentUser(req);

        UserList theUserListCurrentUser = userListService.getOneUserListByIdForCurrentUser(userListAnimeDTO.idUserList, currentUser);

        if (theUserListCurrentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ERROR_ADD_ANIME_TO_USER_LIST, HttpServletResponse.SC_NOT_FOUND, false);
            return;
        }

        Anime animeToAdd = animeService.getByIdApi(userListAnimeDTO.idApiAnime);
        List<Anime> animeList = theUserListCurrentUser.getAnimes(); // récupération les animés dans la liste de l'utilisateur
        if (animeList.contains(animeToAdd)) {
            responseService.errorF(res, Constantes.ErrorMessage.ERROR_ADD_ANIME_ALSO_EXIST, 404, false);
            return;
        }
        UserList ul = userListService.addAnimeToUserList(animeToAdd, animeList, theUserListCurrentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.ADD_ANIME_ON_USER_LIST, animeToAdd.getNomTraduit(), theUserListCurrentUser.getNom()), ul);
    }

    @GetMapping("/{idList}/animes")
    public void getAllAnimeOnUserList(@PathVariable Long idList, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        List<Anime> animeList = userListService.getAllAnimeByUserList(currentUser, idList);
        if (animeList == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_ANIME_FETCH, 404, false);
            return;
        }

        responseService.successF(res, String.format(Constantes.SuccessMessage.ALL_ANIME_ON_LIST, animeList.size()), animeList);
    }

    @DeleteMapping("/{idList}")
    public void deleteUserList(@PathVariable Long idList, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        boolean isDeletable = userListService.deleteListByIdList(idList,currentUser);

        if (!isDeletable) {
            responseService.errorF(res, Constantes.ErrorMessage.LIST_USER_NOT_EXIST_OR_CANT_DELETABLE, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }

        responseService.successF(res,Constantes.SuccessMessage.DELETE_USERLIST_OK, isDeletable);
    }

    @DeleteMapping("/{iduserlist}/animes/{idanime}")
    public void deleteAnimeList(@PathVariable Long iduserlist,@PathVariable Long idanime,HttpServletRequest req,HttpServletResponse res) throws IOException, CustomErrorMessageException {

        if(iduserlist == null || iduserlist<=0 || idanime == null || idanime <=0){
            responseService.errorF(res,Constantes.ErrorMessage.PARAMETER_NOT_VALID,HttpServletResponse.SC_NOT_ACCEPTABLE, false);
            return;
        }

        User currentUser = Helpers.getCurrentUser(req);
        UserList userList = userListService.deleteAnimeList(iduserlist,idanime,currentUser);

        if(userList == null){
            responseService.errorF(res,Constantes.ErrorMessage.USER_LIST_BAD_SAVE_AFTER_DELETE_ANIME,HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false);
            return;
        }

        responseService.successF(res,Constantes.SuccessMessage.DELETE_ANIME_ON_USERLIST_OK, userList);
    }
}
