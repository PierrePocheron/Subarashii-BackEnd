package com.jfam.subarashii.services;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.repositories.EpisodeRepository;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodeService {

    @Autowired
    HttpClient httpClient;

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    AnimeService animeService;


    @Autowired
    EpisodeRepository episodeRepository;


    /*** Je récupère l'anime et je retourne tous les épisodes associés
     * @param idApiAnime
     * @return la liste d'épisode de l'animé
     */
    public List<Episode> GetEpisodesAnimeBySaisonId(Long idApiAnime,Long idApiSaison){

        Anime anime = animeRepository.findByIdApi(idApiAnime);
        final Anime finalAnime = anime == null ? animeService.getByIdApi(idApiAnime) : anime;


        JsonObject result = httpClient.GetQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_GET_EPISODE_BY_ID_ANIME_AND_SEASON,idApiAnime,idApiSaison ));
        JsonArray jsonEpisodeArray = result.get("episodes").getAsJsonArray();

        List<Episode> episodeList = new ArrayList<>();
        jsonEpisodeArray.forEach((jsonEpisode) ->{
            Episode episode = new Episode(jsonEpisode.getAsJsonObject(), finalAnime);
            episodeList.add(episode);
        });

        return episodeRepository.saveAll(episodeList);
    }

    public Episode GetById(long id){
        return null;
    }

    /*** Je récupère l'anime et la saison et je retourne tous les épisodes associés à la saison
     * @param IdAnime
     * @return la liste d'épisode de l'animé
     */
    public List<Episode> GetEpisodesForOneSeason(Long IdAnime , Long IdSeason){
        return null;
    }

}
