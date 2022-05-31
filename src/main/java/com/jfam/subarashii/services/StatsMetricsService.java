package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StatsMetricsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimeCommentRepository animeCommentRepository;

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Integer getCountUsersByRole(String role){
        return userRepository.countByRole(role);
    }

    public Long getCountComments(){
        return animeCommentRepository.count();
    }

    public Long getCountAnimes(){
        return animeRepository.count();
    }

    public Long getCountEpisodes(){
        return episodeRepository.count();
    }

    public Long getCountGenres(){
        return genreRepository.count();
    }
}
