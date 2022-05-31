package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.entities.api.ApiPaginationResults;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.EpisodeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
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
    public void getById(@PathVariable long idapi, HttpServletResponse res) throws IOException, ResourceApiNotFoundException, ParseException {
        Anime anime = animeService.getByIdApi(idapi);
        responseService.successF(res,Constantes.SuccessMessage.ANIME_FIND, anime);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_GET_SEASON_BY_ID_API)
    @GetMapping("/{idanime}/season/{idseason}")
    public void getByAllEpisodeByIdAnimeAndSeason(@PathVariable long idanime, @PathVariable long idseason, HttpServletResponse res) throws IOException, ResourceApiNotFoundException, ParseException {
        List<Episode> episodeList = episodeService.getEpisodesAnimeBySaisonId(idanime,idseason);
        responseService.successF(res,Constantes.SuccessMessage.EPISODE_FIND, episodeList);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_DISCOVER_ANIME)
    @GetMapping(value = "/discover")
    public void discoverAnimed(@RequestParam Optional<Integer> page, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        Integer pageNb = page.isEmpty() ? new Random().nextInt(Constantes.ApiMovie.MAX_PAGE_FOR_DISCOVER_JAPAN_ANIMATION) : page.get();
        ApiPaginationResults resultSearch = animeService.getDiscoverAnime(pageNb);

        if(resultSearch == null || resultSearch.results == null || resultSearch.results.isEmpty())
        {
            responseService.errorF(res,Constantes.ErrorMessage.ANIME_NOT_FOUND, HttpServletResponse.SC_NOT_FOUND,true);
            return;
        }

        responseService.successF(res,Constantes.SuccessMessage.ANIME_DISCOVER_OK, resultSearch);
    }

    @GetMapping("/search")
    public void simpleSearchAnimed(@RequestParam Map<String,String> allParams, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {

        // clear les params envoyé avec une valeur vide ou avec des espaces blancs
        allParams.values().removeIf(val->val.isBlank() || val.isEmpty());

        if(allParams.isEmpty()){
            responseService.errorF(res,Constantes.ErrorMessage.ANY_PARAMETER_PROVIDED,HttpServletResponse.SC_NOT_ACCEPTABLE, false);
            return;
        }

        List<String> unauthorizedParams =  Helpers.getElementInListNotInMapParams(allParams, Constantes.LIST_QUERY_PARAMS_FOR_SIMPLE_SEARCH);
        if(unauthorizedParams.isEmpty())
        {
            responseService.errorF(res,Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_UNAUTHORIZED, unauthorizedParams);
            return;
        }

        ApiPaginationResults resultSearch = animeService.simpleSearchAnime(allParams);
        if(resultSearch.results == null || resultSearch.results.isEmpty())
        {
            responseService.errorF(res,Constantes.ErrorMessage.ANIME_NOT_FOUND, HttpServletResponse.SC_NOT_FOUND,true);
            return;
        }


        responseService.successF(res, String.format(Constantes.SuccessMessage.SEARCH_ANIME_FIND,  resultSearch.results.isEmpty()), resultSearch);
    }



    @GetMapping("/fullsearch")
    public void fullSearchAnimed(@RequestParam Map<String,String> allParams, HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        // clear les params envoyé avec une valeur vide ou avec des espaces blancs
        allParams.values().removeIf(val->val.isBlank() || val.isEmpty());

        if(allParams.isEmpty()){
            responseService.errorF(res,Constantes.ErrorMessage.ANY_PARAMETER_PROVIDED,HttpServletResponse.SC_NOT_ACCEPTABLE, false);
            return;
        }
        List<String> UnauthorizedParams =  Helpers.getElementInListNotInMapParams(allParams, Constantes.LIST_QUERY_PARAMS_FOR_FULL_SEARCH);
        if(UnauthorizedParams.isEmpty())
        {
            responseService.errorF(res,Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_UNAUTHORIZED, UnauthorizedParams);
            return;
        }

        ApiPaginationResults resultSearch  = animeService.complexeSearchAnime(allParams);
        if(resultSearch.results == null || resultSearch.results.isEmpty())
        {
            responseService.errorF(res,Constantes.ErrorMessage.ANIME_NOT_FOUND, HttpServletResponse.SC_NOT_FOUND,true);
            return;
        }
        responseService.successF(res,Constantes.SuccessMessage.COMPLEXE_SEARCH_OK,resultSearch);

    }





}
