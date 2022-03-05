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
            String route = String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID, idApi);
            JsonObject jsonObject = httpClient.GetQuery(route);
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
    public List<Anime> simpleSearchAnime(String name) throws ResourceApiNotFoundException {

        JsonObject queryResult = httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SEARCH_SIMPLE_SEARCH_ANIME, name));
        JsonArray jsonArrayAnime = getAnimeOnSeriesFetchAfterSearch(queryResult);

        List<Anime> animeList = new ArrayList<>();
        jsonArrayAnime.forEach((anim) -> {
            Long id = anim.getAsJsonObject().get(Constantes.ApiMovie.JSON_KEY_ID).getAsLong();
            Anime anime = null;
            try {
                anime = this.getByIdApi(id);
            } catch (ResourceApiNotFoundException e) {
                e.printStackTrace();
            }
            animeList.add(anime);
        });
        return animeList;
    }

    /**
     * Recherche un animé complexe:
     *
     * @return
     */
    public Map<String, Object> searchComplexeAnime(Map<String, String> allParams) throws ResourceApiNotFoundException {
        String query = getQueryFromMap(allParams);
        JsonObject jsonObject = httpClient.GetQuery(Constantes.ApiMovie.ROUTE_SEARCH_COMPLEXE_SEARCH_ANIME_WITHOUT_PARAMS + query);
        return new Gson().fromJson(jsonObject.toString(), Map.class);
    }

    public Discover getDiscoverAnime(int Page) throws ResourceApiNotFoundException {
        JsonObject response = httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_DISCOVER_ANIME, Page));

        Gson gson = new Gson();
        Discover discover = gson.fromJson(response.toString(), Discover.class);
        return discover;
    }

    //region === PRIVATE METHOD ===
    private String getQueryFromMap(Map<String, String> allParams) {
        StringBuilder queryBuilder = new StringBuilder();
        allParams.forEach((k, v) -> {
            if (queryBuilder.toString().isEmpty()) {
                queryBuilder.append(String.format(Constantes.ApiMovie.QUERY_PARAMS_SYNTAX_FIRST_PARAMS, k, v));
                return;
            }
            queryBuilder.append(String.format(Constantes.ApiMovie.QUERY_PARAMS_SYNTAX, k, v));
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
