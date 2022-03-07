package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/episodes")
public class EpisodeController {

    @Autowired
    ResponseService responseService;

    @Autowired
    EpisodeService episodeService;

    @PutMapping("/{idApiEpisode}/see")
    public void PutEpisodeSeeByUser(@PathVariable Long idApiEpisode, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = (User) req.getAttribute(Constantes.Keys.USER);

        if(idApiEpisode == null || idApiEpisode<0 ){
            responseService.ErrorF(res, Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_NOT_ACCEPTABLE,false);
        }

        Boolean isActiveSee = episodeService.UpdateUserEpisodeSeeByIdApi(currentUser, idApiEpisode);

        if(isActiveSee == null){
            responseService.ErrorF(res, "Aucun épisode n'a été trouvé",HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }


        responseService.SuccessF(res,"ok",true);
    }
}
