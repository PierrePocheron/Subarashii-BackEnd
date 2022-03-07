package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.View;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.ViewService;
import com.jfam.subarashii.utils.Constantes;
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



    @GetMapping("/all")
    public void GetAllViewByCurrentUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute(Constantes.Keys.USER);

        List<View> viewList = viewService.getAllView(currentUser);
        responseService.SuccessF(res,"La liste des épisodes vu",viewList);
    }

    @PutMapping("{idApiAnime}/{idApiEpisode}/see")
    public void PutEpisodeSeeByUser(@PathVariable Long idApiAnime ,@PathVariable Long idApiEpisode, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute(Constantes.Keys.USER);

        if(idApiAnime == null || idApiAnime == 0 ||  idApiEpisode == null || idApiEpisode<0 ){
            responseService.ErrorF(res, Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_NOT_ACCEPTABLE,false);
        }

        Boolean isActiveSee = viewService.updateUserViewByIdApiEpisode(currentUser,idApiAnime, idApiEpisode);

        if(isActiveSee == null){
            responseService.ErrorF(res, Constantes.ErrorMessage.EPISODE_NOT_FOUND,HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }

        String message = isActiveSee ? Constantes.SuccessMessage.EPISODE_ADD_VIEW : Constantes.SuccessMessage.EPISODE_REMOVE_VIEW;
        responseService.SuccessF(res,message,isActiveSee);
    }
}
