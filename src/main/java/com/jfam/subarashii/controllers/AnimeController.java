package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    EpisodeService episodeService;




    @GetMapping("/{id}")
    public void GetById(@PathVariable long id,HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        Anime anime = animeService.getByIdApi(id);
        responseService.SuccessF(res,"l'animé a été trouvé", anime);
    }


    @GetMapping("/{idanime}/season/{idseason}")
    public void GetByAllEpisodeByIdAnimeAndSeason(@PathVariable long idanime,@PathVariable long idseason, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        List<Episode> episodeList = episodeService.GetEpisodesAnimeBySaisonId(idanime,idseason);
        responseService.SuccessF(res,"les épisodes ont été trouvé", episodeList);
    }
}
