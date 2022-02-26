package com.jfam.subarashii.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.HttpClient;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.repositories.AnimeRepository;
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


    public Anime getByIdApi(long id){
        Anime anime  = databaseFetch(id);
        return anime == null ? apiFetch(id) : anime;
    }

    /**
     * Recherche un animé par différent critère comme :
     * le nom
     * @return
     */
    public Anime SearchAnime(){
        return null;
    }


    private Anime databaseFetch(Long id){
        return animeRepository.findByIdApi(id);
    }

    private Anime apiFetch(Long id){
        String route = String.format(Constantes.ApiMovie.ROUTE_SERIES_DETAILS_BY_ID,id);
        JsonObject jsonObject = httpClient.GetQuery(route);
        Anime animeApi = new Anime(jsonObject);
        return animeRepository.save(animeApi);
    }

}
