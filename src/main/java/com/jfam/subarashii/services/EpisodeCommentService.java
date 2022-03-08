package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.EpisodeComment;
import com.jfam.subarashii.repositories.EpisodeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeCommentService {

    @Autowired
    EpisodeCommentRepository episodeCommentRepository;

    public List<EpisodeComment> getCommentEpisodeByIdAnime(Long idEpisode) {
        return episodeCommentRepository.findAllByAnime_IdApi(idEpisode);



    }

}
