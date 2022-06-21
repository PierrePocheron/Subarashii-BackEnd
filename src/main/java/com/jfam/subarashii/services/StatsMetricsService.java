package com.jfam.subarashii.services;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import com.jfam.subarashii.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private UserListRepository userListRepository;


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

    public Long getCurrentUserCountAnimeByStatus(String status, User currentUser){
        return userListRepository.getCurrentUserCountAnimeByStatus(status, currentUser.getIdUser());
    }

    public Long getCurrentUserCountComments(User currentUser){
        return animeCommentRepository.getCurrentUserCountComments(currentUser.getIdUser());
    }
}
