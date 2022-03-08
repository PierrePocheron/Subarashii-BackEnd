package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.dto.AnimeCommentDTO;
import com.jfam.subarashii.services.AnimeCommentService;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/animes-comments")
@Tag(name = "AnimeComments")
public class AnimeCommentController {



    @Autowired
    AnimeCommentService animeCommentService;

    @Autowired
    ResponseService responseService;

    @GetMapping("/{idanime}")
    public void GetAnimeComments(@PathVariable long idanime,HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<AnimeComment> animeCommentList = animeCommentService.getCommentByIdAnime(idanime);
        if(animeCommentList.size() == 0){
            responseService.SuccessF(res,"commentaires inexistants", animeCommentList);
            return;
        }
        User currentUser  = Helpers.getCurrentUser(req);
        List<AnimeCommentDTO> animeCommentDTOList =  animeCommentList.stream().map(animeComment -> new AnimeCommentDTO(animeComment, currentUser)).collect(Collectors.toList());




        responseService.SuccessF(res,"le commentaire a été trouvé", animeCommentDTOList);

    }

    @PostMapping
    public void CreateAnimeComments(@RequestBody AnimeCommentDTO animeCommentDTO, HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException, ResourceApiNotFoundException {
        if(animeCommentDTO == null || animeCommentDTO.getIdApiAnime() < 0 || animeCommentDTO.getContenu().isEmpty())
        {
            responseService.ErrorF(res, Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_BAD_REQUEST,false);
            return;
        }
        User currentUser  = Helpers.getCurrentUser(req);
       AnimeComment resultAnimeComment =  animeCommentService.createAnimeComment(currentUser,animeCommentDTO);
        if(resultAnimeComment == null){
            responseService.ErrorF(res, "commentaire animé null",HttpServletResponse.SC_BAD_REQUEST,false);
            return;
        }

        responseService.SuccessF(res,"le commentaire a été ajouté", resultAnimeComment);


    }
}
