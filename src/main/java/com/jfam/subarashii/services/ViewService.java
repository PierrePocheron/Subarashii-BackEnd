package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.Episode;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.View;
import com.jfam.subarashii.repositories.EpisodeRepository;
import com.jfam.subarashii.repositories.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewService {

    @Autowired
    ViewRepository viewRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    public Boolean updateUserViewByIdApiEpisode(User user , Long IdApiAnime, Long idApiEpisode){
        Episode episode = episodeRepository.findByIdApi(idApiEpisode);
        if(episode == null || !episode.getIdApiAnime().equals(IdApiAnime))
            return null;

        View view = viewRepository.findByIdApiAnimeAndIdApiEpisodeAndUser(IdApiAnime,idApiEpisode , user);

        // si jamais vue
        if (view == null){
            View v = new View();
            v.setUser(user);
            v.setIdApiEpisode(idApiEpisode);
            v.setIdApiAnime(IdApiAnime);
            viewRepository.save(v);
            return true;
        }

        boolean has_see = view.isSee();
        view.setSee(!has_see);
        viewRepository.save(view);
        return !has_see;
    }

    public List<View> getAllView(User user){
        return viewRepository.findAllByUserAndAndSeeIsTrue(user);
    }


    public List<View> getAllViewByIdApiAnime(User user, Long idApiAnime){
        return viewRepository.findAllByUserAndIdApiAnimeAndSeeIsTrue(user, idApiAnime);
    }



}
