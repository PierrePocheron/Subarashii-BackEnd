package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.repositories.AnimeCommentRepository;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.repositories.UserRepository;
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

    public Integer getCountUsersByRole(String role){
        return userRepository.countByRole(role);
    }

    public Long getCountComments(){
        return animeCommentRepository.count();
    }

    public Long getCountAnimes(){
        return animeRepository.count();
    }
}
