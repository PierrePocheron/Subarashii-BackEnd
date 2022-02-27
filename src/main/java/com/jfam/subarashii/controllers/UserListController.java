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


    @Operation(summary = "Retourne tous les listes de l'utilisateur courant")
    @GetMapping("/mylist")
    public void getCurrentUserList(HttpServletRequest req,HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute("user");
        List<UserList> listUserLists=  userListService.getCurrentUserList(currentUser);
        responseService.SuccessF(res, "La liste de l'utilisateur a correctement été récupéré",listUserLists);
    }

    @Operation(summary = "Permet de créer uen liste personnalisé pour l'utilsiateur courant")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createCustomList(@RequestBody  UserList userList, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute("user");
        UserList customUserList =  userListService.createCustomList(currentUser,userList.getNom());
        responseService.SuccessF(res, "La liste custom a correctement été crée",customUserList);
    }

    @Operation(summary = "Ajoute un animé (grâce à son idapi) à la liste (int envoyé en paramètre) de l'utilisateur courant")
    @PutMapping("/addanime")
    public void addAnimeToUserList(@RequestBody UserListAnimeDTO userListAnimeDTO, HttpServletRequest req, HttpServletResponse res ) throws IOException, ResourceApiNotFoundException {
        User currentUser = (User) req.getAttribute("user");

        UserList theUserListCurrentUser = userListService.GetOneUserListForUser(userListAnimeDTO.idUserList, currentUser);

        if(theUserListCurrentUser == null){
            responseService.ErrorF(res,"La liste dans laquel vous souhaité ajouter l'animé n'existe pas ou n'appartient pas à l'utilisateur",404,false);
            return;
        }

        Anime animeToAdd =  animeService.getByIdApi(userListAnimeDTO.IdApiAnime);

        // récupération des animés dans la liste :
        List<Anime> animeList= theUserListCurrentUser.getAnimes();

        // si l'animé est déjà présent :
        if (animeList.contains(animeToAdd))
        {
            responseService.ErrorF(res,"L'animé est déjà présent dans la liste",404,false);
            return;
        }

        // j'ajoute l'anime dans la liste
        animeList.add(animeToAdd);

        // je modifie la liste des animés dans la liste
        theUserListCurrentUser.setAnimes(animeList);

        userListService.updateUserList(theUserListCurrentUser);

        responseService.SuccessF(res, String.format(Constantes.SuccessMessage.ADD_ANIME_ON_USER_LIST, animeToAdd.getNom(), theUserListCurrentUser.getNom()),true);
    }
}
