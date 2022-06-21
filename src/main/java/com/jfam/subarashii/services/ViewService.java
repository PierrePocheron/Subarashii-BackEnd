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

    public Boolean updateUserViewByIdApiEpisode(User user , Long idApiAnime, Long idApiEpisode){
        Episode episode = episodeRepository.findByIdApi(idApiEpisode);
        if(episode == null || !episode.getIdApiAnime().equals(idApiAnime))
            return null;

        View view = viewRepository.findByIdApiAnimeAndIdApiEpisodeAndUser(idApiAnime,idApiEpisode , user);

        // si jamais vue
        if (view == null){
            View v = new View();
            v.setUser(user);
            v.setIdApiEpisode(idApiEpisode);
            v.setIdApiAnime(idApiAnime);
            viewRepository.save(v);
            return true;
        }

        boolean hasSee = view.isSee();
        view.setSee(!hasSee);
        viewRepository.save(view);
        return !hasSee;
    }

    public List<View> getAllViewByIdApiAnime(User user, Long idApiAnime){
        return viewRepository.findAllByUserAndIdApiAnimeAndSeeIsTrue(user, idApiAnime);
    }



}
