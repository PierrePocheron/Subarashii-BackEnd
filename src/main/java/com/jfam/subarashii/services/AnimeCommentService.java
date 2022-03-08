package com.jfam.subarashii.services;


import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.dto.AnimeCommentDTO;
import com.jfam.subarashii.repositories.AnimeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class AnimeCommentService {



    @Autowired
    AnimeCommentRepository animeCommentRepository;

    @Autowired
    AnimeService animeService;

    public List<AnimeComment> getCommentByIdAnime(Long idAnime) {
      return animeCommentRepository.findAllByAnime_IdApi(idAnime);

    }

    public AnimeComment createAnimeComment(User user, AnimeCommentDTO animeCommentDTO) throws ParseException, ResourceApiNotFoundException {
       Anime anime =  animeService.getByIdApi(animeCommentDTO.getIdApiAnime());
       //si je n'ai pas d'anime retourne null
        if(anime == null) return null;

      AnimeComment animeCommentToSave =  animeCommentDTO.toEntity(user,anime);
      return animeCommentRepository.save(animeCommentToSave);



    }
}
