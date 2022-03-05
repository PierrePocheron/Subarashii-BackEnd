package com.jfam.subarashii.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Genre;
import com.jfam.subarashii.entities.api.Discover;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import com.jfam.subarashii.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jfam.subarashii.utils.Constantes.ApiMovie.PARAMS_QUESTION_MARK;

@Service
public class AnimeService {

    @Autowired
    AnimeRepository animeRepository;

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
    public Anime getByIdApi(long idApi) throws ResourceApiNotFoundException {

        Anime anime = animeRepository.findByIdApi(idApi);

        if (anime == null) {
            JsonObject jsonObject = httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID, idApi));
            Anime animeApi = new Anime(jsonObject);

            // récupère les genres associés à l'animé avant de le sauvegarder
            List<Genre> genresList = genreService.convertJsonObjectGenreToListGenre(jsonObject, Constantes.Keys.USER_GENRES);
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
    public Map<String, Object> simpleSearchAnime(Map<String, String> allParams) throws ResourceApiNotFoundException {

        String query = getQueryFromMap(allParams);
        JsonObject queryResult = httpClient.GetQuery(Constantes.ApiMovie.ROUTE_SIMPLE_SEARCH_ANIME_WITHOUT_PARAMS + query);
        JsonArray jsonArrayAnime = getAnimeOnSeriesFetchAfterSearch(queryResult);

        var map =  Helpers.ConvertJsonElementToMap(jsonArrayAnime);
        return map;
    }

    /**
     * Recherche un complexe animé:
     * @return map with all result
     */
    public Map<String, Object> searchComplexeAnime(Map<String, String> allParams) throws ResourceApiNotFoundException {
        String query = getQueryFromMap(allParams);
        JsonObject queryResult = httpClient.GetQuery(Constantes.ApiMovie.ROUTE_COMPLEXE_SEARCH_ANIME_WITHOUT_PARAMS + query);
        return new Gson().fromJson(queryResult.toString(), Map.class);
    }

    /**
     * Fetch anime and convert to Discover result
     * @param Page
     * @return Discover object (api objet with page and other infos)
     * @throws ResourceApiNotFoundException
     */
    public Discover getDiscoverAnime(int Page) throws ResourceApiNotFoundException {
        JsonObject queryResult = httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_DISCOVER_ANIME, Page));
        Gson gson = new Gson();
        return gson.fromJson(queryResult.toString(), Discover.class);
    }

    //region === PRIVATE METHOD ===

    /**
     * Créer à partir d'un map un string représentant les paramètres d'une query
     * @param allParams
     * @return
     */
    private String getQueryFromMap(Map<String, String> allParams) {
        StringBuilder queryBuilder = new StringBuilder();
        allParams.forEach((k, v) -> {
            // si je suis le premier paramètre de la query, je n'ai pas d'esperluette
            if (queryBuilder.toString().isEmpty()) {
                queryBuilder.append(String.format(Constantes.ApiMovie.FIRST_QUERY_PARAMS_SYNTAX_, k, v));
                return;
            }
            queryBuilder.append(String.format(Constantes.ApiMovie.OTHER_QUERY_PARAMS_SYNTAX, k, v));
        });
        queryBuilder.insert(0, PARAMS_QUESTION_MARK);
        return queryBuilder.toString();
    }

    /**
     * Récupère après la recherche, seulement les série de type animé
     * @param queryResult
     */
    private JsonArray getAnimeOnSeriesFetchAfterSearch(JsonObject queryResult) {

        JsonArray arrayResult = queryResult.get(Constantes.ApiMovie.JSON_KEY_RESULT).getAsJsonArray();
        JsonArray jsonArrayAnime = new JsonArray();

        arrayResult.forEach((result) -> {
            // me donne dans chaque animé la liste des genres :
            JsonArray arrayIdGenre = result.getAsJsonObject().get(Constantes.ApiMovie.JSON_KEY_GENRE_IDS).getAsJsonArray();
            List<Genre> genresList = genreService.convertJsonArrayIdGenreToListGenre(arrayIdGenre);

            // donne moi toutes les série qui ont un id genre à 16
            if (genresList.stream().anyMatch(x -> x.getIdApi() == Constantes.ApiMovie.ANIMATION_ID_GENRE)) {
                jsonArrayAnime.add(result);
                return;
            }
        });

        return jsonArrayAnime;
    }
    //endregion
}
