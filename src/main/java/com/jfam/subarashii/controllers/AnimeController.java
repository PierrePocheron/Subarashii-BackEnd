package com.jfam.subarashii.controllers;

import com.google.gson.internal.LinkedTreeMap;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
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
    public void GetById(@PathVariable long id,HttpServletResponse res) throws IOException{
        Anime anime = animeService.getByIdApi(id);
        responseService.SuccessF(res,"l'animé a été trouvé", anime);
    }


    @GetMapping("/{idanime}/season/{idseason}")
    public void GetByAllEpisodeByIdAnimeAndSeason(@PathVariable long idanime,@PathVariable long idseason, HttpServletResponse res) throws IOException{
        List<Episode> episodeList = episodeService.GetEpisodesAnimeBySaisonId(idanime,idseason);
        responseService.SuccessF(res,"les épisodes ont été trouvé", episodeList);
    }
}
