package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.entities.api.Discover;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.AnimeCommentService;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(path = "/animes")
@Tag(name = "Anime")
public class AnimeController {

    @Autowired
    AnimeService animeService;

    @Autowired
    AnimeCommentService animeCommentService;

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

    @Operation(summary = "Récupère 20 animés au hasard (se sert de la pagination de l'api)")
    @GetMapping(value = {"/discover","/discover/{idPage}"})
    public void DiscoverAnimed(@PathVariable(required = false) Integer idPage, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        if(idPage == null)
            idPage = new Random().nextInt(Constantes.ApiMovie.MAX_PAGE_FOR_DISCOVER_JAPAN_ANIMATION);

        Discover discover = animeService.getDiscoverAnime(idPage);
        if(discover == null){
            responseService.ErrorF(res,"Aucun animé n'a été trouvé à la page " + idPage, HttpServletResponse.SC_BAD_GATEWAY,false);
        }
        responseService.SuccessF(res,"Une liste d'animé à découvrir a été trouvé", discover);
    }


    @PostMapping("/searchbyname")
    public void SearchAnimedByName(@RequestBody Map<String, String> payload , HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        //https://api.themoviedb.org/3/discover/tv?
        String query = payload.get("query");

        if(query == null)
        {
            responseService.ErrorF(res,"Le paramètre n'est pas celui attendu ou ne possède pas de la valeur", HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }

        List<Anime> animeList = animeService.SearchAnimeByName(query);
        if(animeList.size() == 0)
        {
            responseService.SuccessF(res,"Aucun animé n'a été trouvé...", null);
            return;
        }
        responseService.SuccessF(res,animeList.size() + " ont été trouvé(s)", animeList);
    }

    @PostMapping("/searchbyinfo")
    public void SearchAnimedByOtherInfo(HttpServletRequest req){
        //https://api.themoviedb.org/3/discover/tv?

    }
    @GetMapping("/{idanime}/comments")
    public void GetAnimeComments(@PathVariable long idanime,HttpServletResponse res) throws IOException{
           List<AnimeComment>  animeComment = animeCommentService.getCommentByIdAnime(idanime);
            if(animeComment.size() == 0){
                responseService.SuccessF(res,"commentaires inexistants", animeComment);
                return;
            }
            responseService.SuccessF(res,"le commentaire a été trouvé", animeComment);







    }




}
