package com.jfam.subarashii.services;


import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.repositories.AnimeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeCommentService {



    @Autowired
    AnimeCommentRepository animeCommentRepository;

    public List<AnimeComment> getCommentByIdAnime(Long idAnime) {
      return animeCommentRepository.findAllByAnime_IdApi(idAnime);



    }
}
