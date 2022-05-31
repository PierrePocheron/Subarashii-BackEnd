package com.jfam.subarashii.services;

import com.google.gson.JsonObject;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Genre;
import com.jfam.subarashii.entities.api.ApiPaginationResults;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.repositories.UserListRepository;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import com.jfam.subarashii.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


@Service
public class AnimeService {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    UserListRepository userListRepository;

    @Autowired
    HttpClient httpClient;

    @Autowired
    GenreService genreService;

    private static final Logger logger = LoggerFactory.getLogger(AnimeService.class);

    /**
     * Retourne un animé grâce à son IdApi, si n'est pas présent dans la bdd alors il est récupérer sur l'api puis enregistré en bdd
     *
     * @param idApi
     * @return Retourne l'animé avec ses informations
     * @throws ResourceApiNotFoundException
     */
    public Anime getByIdApi(long idApi) throws ResourceApiNotFoundException, ParseException {

        Anime anime = animeRepository.findByIdApi(idApi);

        if (anime == null) {
            JsonObject jsonObject = httpClient.getQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID, idApi));
            Anime animeApi = new Anime(jsonObject);

            // récupère les genres associés à l'animé avant de le sauvegarder
            List<Genre> genresList = genreService.convertJsonObjectGenreToListGenre(jsonObject, Constantes.Keys.GENRES);
            animeApi.setGenres(genresList);
            anime = animeRepository.save(animeApi);
        }

        return anime;
    }

    /**
     * Simple recherche sur les animés,
     * ⚠ la simple recherche ne permets pas de trié le genre de série récupéré
     * donc un tri par genre (animation) est réalisé manuellement
     * @return une liste d'animé correspondant à la recherche
     */
    public ApiPaginationResults simpleSearchAnime(Map<String, String> allParams) throws ResourceApiNotFoundException {

        String query = Helpers.getQueryFromMap(allParams);
        // récupère des série de tout genre :
        ApiPaginationResults apiPaginationResults = httpClient.getQueryPageableResult(Constantes.ApiMovie.ROUTE_SIMPLE_SEARCH_ANIME_WITHOUT_PARAMS + query);

        if(apiPaginationResults.results != null)
            // du coup je ne veux que les animés:
            apiPaginationResults.results.removeIf(result ->  !result.genreIds.contains(16.0));

        return apiPaginationResults;
    }

    /**
     * Recherche un complexe animé:
     * @return map with all result
     */
    public ApiPaginationResults complexeSearchAnime(Map<String, String> allParams) throws ResourceApiNotFoundException {
        String query = Helpers.getQueryFromMap(allParams);
        return httpClient.getQueryPageableResult(Constantes.ApiMovie.ROUTE_COMPLEXE_SEARCH_ANIME_WITHOUT_PARAMS + query);
    }

    /**
     * Fetch anime and convert to ApiPaginationResults result
     * @param page
     * @return ApiPaginationResults object (api objet with page and other infos)
     * @throws ResourceApiNotFoundException
     */
    public ApiPaginationResults getDiscoverAnime(int page) throws ResourceApiNotFoundException {
        ApiPaginationResults apiPaginationResults = httpClient.getQueryPageableResult(String.format(Constantes.ApiMovie.ROUTE_SERIES_DISCOVER_ANIME, page));
        return apiPaginationResults;
    }




}
