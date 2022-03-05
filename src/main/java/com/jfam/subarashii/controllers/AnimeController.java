package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.entities.api.Discover;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
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
    @PostMapping("/search")
    public void SearchAnimedByName(@RequestParam Optional<String> query , HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        if(query.isEmpty())
        {
            responseService.ErrorF(res,Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED, HttpServletResponse.SC_NOT_ACCEPTABLE,false);
            return;
        }

        List<Anime> animeList = animeService.simpleSearchAnime(query.get());
        if(animeList.size() == 0)
        {
            responseService.SuccessF(res,Constantes.ErrorMessage.ANIME_NOT_FOUND, true);
            return;
        }
        responseService.SuccessF(res, String.format(Constantes.SuccessMessage.SEARCH_ANIME_FIND,  animeList.size()), animeList);
    }


    @PostMapping("/fullsearch")
    public void fullSearchAnimed(@RequestParam Map<String,String> allParams, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        if(allParams.size() ==0){
            responseService.ErrorF(res,Constantes.ErrorMessage.ANY_PARAMETER_PROVIDED,HttpServletResponse.SC_NOT_ACCEPTABLE, false);
            return;
        }
        List<String> UnauthorizedParams =  Helpers.GetElementInListNotInMapParams(allParams, Constantes.LIST_QUERY_PARAMS_FOR_FULL_SEARCH);
        if(UnauthorizedParams.size() != 0)
        {
            responseService.ErrorF(res,Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_UNAUTHORIZED, UnauthorizedParams);
            return;
        }

        Map<String, Object> resultSearch = animeService.searchComplexeAnime(allParams);
        responseService.SuccessF(res,Constantes.SuccessMessage.COMPLEXE_SEARCH_OK,resultSearch);

    }

}
