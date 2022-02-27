package com.jfam.subarashii.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AnimeService {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    HttpClient httpClient;

    private static final Logger logger = LoggerFactory.getLogger(AnimeService.class);

    public Anime getByIdApi(long id) throws ResourceApiNotFoundException {

        Anime anime  = animeRepository.findByIdApi(id);

        // if isn't on database fetch to api
        if(anime == null){
            String route = String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID,id);
            JsonObject jsonObject = httpClient.GetQuery(route);
            Anime animeApi = new Anime(jsonObject);
            anime = animeRepository.save(animeApi);
        }

        return anime;
    }

    /**
     * Recherche un animé par différent critère comme :
     * le nom
     * @return
     */
    public Anime SearchAnime(){
        return null;
    }


    public List<Anime> getDiscoverAnime(int Page) throws ResourceApiNotFoundException {

        JsonObject jsonObject =  httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_DISCOVER_ANIME,Page));
        JsonArray jsonArrayResult = getjsonArrayResultDiscovery(jsonObject);

        List<Anime> animeList = new ArrayList<>();

        jsonArrayResult.forEach((result)->{
            Long idApiAnime = result.getAsJsonObject().get("id").getAsLong();
            try {
                logger.info(String.format("id api anime : %d , dans la page %d", idApiAnime, Page));
                Anime anime =  getByIdApi(idApiAnime);
                animeList.add(anime);

            } catch (ResourceApiNotFoundException e) {
                e.printStackTrace();
            }
        });

        return animeList;
    }


    //region PRIVATE METHOD
    private Anime apiFetch(Long id) throws ResourceApiNotFoundException {
        String route = String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID,id);
        JsonObject jsonObject = httpClient.GetQuery(route);
        Anime animeApi = new Anime(jsonObject);
        return animeRepository.save(animeApi);
    }

    private JsonArray getjsonArrayResultDiscovery(JsonObject jsonObject){
        return jsonObject.get("results").getAsJsonArray();
    }

    //endregion

}
