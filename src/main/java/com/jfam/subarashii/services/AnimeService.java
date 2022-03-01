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

@Service
public class AnimeService {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    HttpClient httpClient;

    @Autowired
    GenreService genreService;

    private static final Logger logger = LoggerFactory.getLogger(AnimeService.class);

    public Anime getByIdApi(long id) throws ResourceApiNotFoundException {

        Anime anime  = animeRepository.findByIdApi(id);

        // if isn't on database fetch to api
        if(anime == null){
            String route = String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID,id);
            JsonObject jsonObject = httpClient.GetQuery(route);
            Anime animeApi = new Anime(jsonObject);

            List<Genre> genresList = genreService.convertJsonObjectGenreToListGenre(jsonObject,"genres");
            animeApi.setGenres(genresList);
            anime = animeRepository.save(animeApi);
        }

        return anime;
    }

    /**
     * Recherche un animé par son nom :
     * le nom
     * @return
     */
    public List<Anime> SearchAnimeByName(String name) throws ResourceApiNotFoundException {
        JsonObject jsonObject = httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SEARCH_ANIME_BY_NAME, name));
        JsonArray jsonArrayAnime =  checkAllSerieFetch(jsonObject);

        List<Anime> animeList = new ArrayList<>();
        jsonArrayAnime.forEach((anim)->{
            Long id = anim.getAsJsonObject().get("id").getAsLong();
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

    public Discover getDiscoverAnime(int Page) throws ResourceApiNotFoundException {
        JsonObject response =  httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_DISCOVER_ANIME,Page));

        Gson gson = new Gson();
        Discover discover = gson.fromJson(response.toString(), Discover.class);
        return discover;
    }
    //region === PRIVATE METHOD ===

    private JsonArray getjsonArrayResultDiscovery(JsonObject jsonObject){
        return jsonObject.get("results").getAsJsonArray();
    }

    private JsonArray checkAllSerieFetch(JsonObject jsonObject){

        JsonArray arrayResult = jsonObject.get("results").getAsJsonArray();
        JsonArray jsonArrayAnime = new JsonArray();

        // boucle sur chaque série récupérée
        arrayResult.forEach((result)-> {
            JsonArray arrayIdGenre = result.getAsJsonObject().get("genre_ids").getAsJsonArray();
            List<Genre> genresList = genreService.convertJsonArrayIdGenreToListGenre(arrayIdGenre);
            if(genresList.stream().anyMatch(x->x.getIdApi() == 16)){
                jsonArrayAnime.add(result);
                return;
            }
        });

        return jsonArrayAnime;
    }

    //endregion

}
