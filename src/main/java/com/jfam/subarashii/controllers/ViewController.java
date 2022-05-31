package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.View;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.ViewService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("views")
public class ViewController {

    @Autowired
    ResponseService responseService;

    @Autowired
    ViewService viewService;

    @PutMapping("/animes/{idApiAnime}/episodes/{idApiEpisode}")
    public void putEpisodeSeeByUser(@PathVariable Long idApiAnime , @PathVariable Long idApiEpisode, HttpServletRequest req, HttpServletResponse res) throws IOException {
        if(idApiAnime == null || idApiAnime == 0 ||  idApiEpisode == null || idApiEpisode<0 ){
            responseService.errorF(res, Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }
        User currentUser = Helpers.getCurrentUser(req);


        Boolean isActiveSee = viewService.updateUserViewByIdApiEpisode(currentUser,idApiAnime, idApiEpisode);

        if(isActiveSee == null){
            responseService.errorF(res, Constantes.ErrorMessage.EPISODE_NOT_FOUND,HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }

        String message = isActiveSee ? Constantes.SuccessMessage.EPISODE_ADD_VIEW : Constantes.SuccessMessage.EPISODE_REMOVE_VIEW;
        responseService.successF(res,message,isActiveSee);
    }


    @GetMapping("/animes/{idApiAnime}")
    public void getAllViewByAnime(@PathVariable Long idApiAnime , HttpServletRequest req, HttpServletResponse res) throws IOException {
        if(idApiAnime == null || idApiAnime == 0){
            responseService.errorF(res, Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }
        User currentUser = Helpers.getCurrentUser(req);

        List<View> viewList =  viewService.getAllViewByIdApiAnime(currentUser,idApiAnime);

        responseService.successF(res,String.format(Constantes.SuccessMessage.FETCH_EPISODE_VIEW_BY_ID_ANIME,viewList.size(),idApiAnime),viewList);
    }
}
