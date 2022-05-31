package com.jfam.subarashii.services;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.repositories.EpisodeRepository;
import com.jfam.subarashii.repositories.UserRepository;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    @Autowired
    UserRepository userRepository;

    /*** Je récupère l'anime et je retourne tous les épisodes associés dans la saison
     * @param idApiAnime
     * @return la liste d'épisode de l'animé
     */
    public List<Episode> getEpisodesAnimeBySaisonId(Long idApiAnime, Long idApiSaison) throws ResourceApiNotFoundException, ParseException {

        Anime anime = animeService.getByIdApi(idApiAnime);
        List<Episode> episodeList = episodeRepository.findAllByAnimeIdApiAndSaison(idApiAnime, idApiSaison);
        if(episodeList.isEmpty())
            return episodeList;
        episodeList = fetchApi(idApiAnime,idApiSaison,anime);
        return episodeRepository.saveAll(episodeList);
    }



    /*** Si je n'ai pas les épisodes en bases je les récupères de l'api
     * @param idApiAnime
     * @param idApiSaison
     * @param anime
     * @return
     */
    private List<Episode> fetchApi(Long idApiAnime,Long idApiSaison , Anime anime) throws ResourceApiNotFoundException {
        JsonObject result = httpClient.getQuery(String.format(Constantes.ApiMovie.ROUTE_SERIES_GET_EPISODE_BY_ID_ANIME_AND_SEASON,idApiAnime,idApiSaison ));
        JsonArray jsonEpisodeArray = result.get(Constantes.ApiMovie.JSON_KEY_EPISODES).getAsJsonArray();
        List<Episode> episodeList = new ArrayList<>();
        jsonEpisodeArray.forEach((jsonEpisode) ->{
            Episode episode = new Episode(jsonEpisode.getAsJsonObject(), anime);
            episodeList.add(episode);
        });
        return episodeRepository.saveAll(episodeList);
    }

}
