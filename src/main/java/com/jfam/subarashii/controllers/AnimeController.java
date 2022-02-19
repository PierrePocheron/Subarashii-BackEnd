package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/animes")
public class AnimeController {

    @Autowired
    AnimeService animeService;

    @Autowired
    ResponseService responseService;

    @GetMapping
    public void GetOne(HttpServletResponse response) throws IOException {
        Anime anime = animeService.getOne();
        if(anime == null){
            responseService.ErrorF(response,"Aucune animé n'à été trouvé",HttpServletResponse.SC_NOT_FOUND, null);
            return;
        }
        responseService.SuccessF(response,"Un animé à été trouvé", anime);
    }

    @GetMapping(path = "/all")
    public void GetAll(HttpServletResponse response) throws IOException {
        List<Anime> animeList = animeService.getAll();
        if(animeList == null || animeList.size() == 0){
            responseService.ErrorF(response,"Aucune animé n'à été trouvé",HttpServletResponse.SC_NOT_FOUND, null);
            return;
        }
        responseService.SuccessF(response,"Des animés ont été trouvés", animeList);
    }


}
