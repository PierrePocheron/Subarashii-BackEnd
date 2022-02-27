package com.jfam.subarashii.services;

import com.google.gson.JsonObject;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    HttpClient httpClient;


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







    private Anime apiFetch(Long id) throws ResourceApiNotFoundException {
        String route = String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID,id);
        JsonObject jsonObject = httpClient.GetQuery(route);
        Anime animeApi = new Anime(jsonObject);
        return animeRepository.save(animeApi);
    }
}
