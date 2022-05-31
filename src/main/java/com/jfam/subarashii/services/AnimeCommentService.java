package com.jfam.subarashii.services;


import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.dto.AnimeCommentDTO;
import com.jfam.subarashii.repositories.AnimeCommentRepository;
import com.jfam.subarashii.repositories.AnimeRepository;
import com.jfam.subarashii.repositories.UserRepository;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class AnimeCommentService {

    @Autowired
    AnimeCommentRepository animeCommentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimeService animeService;
    @Autowired
    AnimeRepository animeRepository;

    public List<AnimeComment> getCommentByIdAnime(Long idAnime) {
      return animeCommentRepository.findAllByAnimeIdApi(idAnime);
    }


    public List<AnimeComment> getAll(){
        return animeCommentRepository.findAll();
    }

    public AnimeComment createAnimeComment(User user, AnimeCommentDTO animeCommentDTO) throws ParseException, ResourceApiNotFoundException {
       Anime anime =  animeService.getByIdApi(animeCommentDTO.getIdApiAnime());
       //si je n'ai pas d'anime retourne null
        if(anime == null) return null;

        AnimeComment animeCommentToSave =  animeCommentDTO.toEntity(user,anime);
        user.getAnimeComments().add(animeCommentToSave);
        return animeCommentRepository.save(animeCommentToSave);
    }

    public Boolean deleteAnimeCommentById(Long idAnimeComment) throws ResponseStatusException{
        Optional<AnimeComment> animeCommentOpt = animeCommentRepository.findById(idAnimeComment);
        if(animeCommentOpt.isPresent()) {
            AnimeComment animeComment = animeCommentOpt.get();
            Optional<Anime> animeOpt = animeRepository.findById(animeComment.getAnime().getId());
            if(animeOpt.isPresent()) {
                Anime anime = animeOpt.get();
                //Anime exist -> remove comment of list
                anime.getAnimeComments().remove(animeComment);
                animeRepository.save(anime);

                //AnimeComment exist -> delete it
                animeCommentRepository.deleteById(idAnimeComment);
                return true;
            } else {
                //Anime doesn't exist -> throw exception
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_ANIME_DOESNT_EXISTS);
            }
        } else {
            //AnimeComment doesn't exist -> throw exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_ANIMECOMMENT_DOESNT_EXISTS);
        }
    }
}
