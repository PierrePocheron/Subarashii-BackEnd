package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/animes")
@Tag(name = "Anime")
public class AnimeController {

    @Autowired
    AnimeService animeService;

    @Autowired
    ResponseService responseService;

    @Autowired
    EpisodeService episodeService;


    @Operation(summary = "Récupère un anime par son id api, s'il n'existe pas en bdd l'ajoute grâce à l'api")
    @GetMapping("/{idapi}")
    public void GetById(@PathVariable long idapi,HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        Anime anime = animeService.getByIdApi(idapi);
        responseService.SuccessF(res,"l'animé a été trouvé", anime);
    }

    @Operation(summary = "Récupère la saison d'un anime grâce à l'id api de l'anime et du numéro de la saison, ajoute en bdd les épisodes et l'anime s'il n'existe pas")
    @GetMapping("/{idanime}/season/{idseason}")
    public void GetByAllEpisodeByIdAnimeAndSeason(@PathVariable long idanime,@PathVariable long idseason, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        List<Episode> episodeList = episodeService.GetEpisodesAnimeBySaisonId(idanime,idseason);
        responseService.SuccessF(res,"les épisodes ont été trouvé", episodeList);
    }


    @GetMapping("/discover")
    public void DiscoverAnimed(HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        int randomPageDiscovery = new Random().nextInt(Constantes.ApiMovie.MAX_PAGE_FOR_DISCOVER_JAPAN_ANIMATION);
        List<Anime> animeList = animeService.getDiscoverAnime(randomPageDiscovery);
        if(animeList == null){
            responseService.ErrorF(res,"Aucun animé n'a été trouvé à la page " + randomPageDiscovery, HttpServletResponse.SC_BAD_GATEWAY,false);
        }
        responseService.SuccessF(res,"Une liste d'animé à découvrir a été trouvé", animeList);
    }
}
