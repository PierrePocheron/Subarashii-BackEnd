package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.entities.api.Discover;
import com.jfam.subarashii.services.AnimeService;
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
import java.util.Optional;
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


    @Operation(summary = Constantes.Swagger.SUMMARY_ANIME_GET_BY_ID_API)
    @GetMapping("/{idapi}")
    public void GetById(@PathVariable long idapi,HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        Anime anime = animeService.getByIdApi(idapi);
        responseService.SuccessF(res,Constantes.SuccessMessage.ANIME_FIND, anime);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_GET_SEASON_BY_ID_API)
    @GetMapping("/{idanime}/season/{idseason}")
    public void GetByAllEpisodeByIdAnimeAndSeason(@PathVariable long idanime,@PathVariable long idseason, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        List<Episode> episodeList = episodeService.GetEpisodesAnimeBySaisonId(idanime,idseason);
        responseService.SuccessF(res,Constantes.SuccessMessage.EPISODE_FIND, episodeList);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_DISCOVER_ANIME)
    @GetMapping(value = "/discover")
    public void DiscoverAnimed(@RequestParam Optional<Integer> page, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {

        Integer pageNb = page.isEmpty() ? new Random().nextInt(Constantes.ApiMovie.MAX_PAGE_FOR_DISCOVER_JAPAN_ANIMATION) : page.get();

        Discover discover = animeService.getDiscoverAnime(pageNb);
        if(discover == null){
            responseService.ErrorF(res,Constantes.ErrorMessage.ANIME_NOT_FOUND_ON_PAGE + pageNb, HttpServletResponse.SC_BAD_GATEWAY,false);
        }
        responseService.SuccessF(res,Constantes.SuccessMessage.ANIME_DISCOVER_OK, discover);
    }



    //TODO : a modifier pour passer en /search?query=oooo&page=1&include_adult=false&
    // bases on : https://developers.themoviedb.org/3/search/search-tv-shows
    @PostMapping("/searchbyname")
    public void SearchAnimedByName(@RequestBody Map<String, String> payload , HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        String query = payload.get("query");

        if(query == null)
        {
            responseService.ErrorF(res,Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED, HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }

        List<Anime> animeList = animeService.SearchAnimeByName(query);
        if(animeList.size() == 0)
        {
            responseService.SuccessF(res,Constantes.ErrorMessage.ANIME_NOT_FOUND, null);
            return;
        }
        responseService.SuccessF(res, String.format(Constantes.SuccessMessage.SEARCH_ANIME_FIND,  animeList.size()), animeList);
    }


    //TODO : a modifier pour en /fullsearch?....
    //based on https://developers.themoviedb.org/3/discover/movie-discover
    @PostMapping("/searchbyinfo")
    public void SearchAnimedByOtherInfo(HttpServletRequest req){

    }





}
