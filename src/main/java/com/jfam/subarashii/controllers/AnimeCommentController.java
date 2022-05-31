package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.dto.AnimeCommentDTO;
import com.jfam.subarashii.services.AnimeCommentService;
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
    public void getAnimeComments(@PathVariable long idanime, HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<AnimeComment> animeCommentList = animeCommentService.getCommentByIdAnime(idanime);
        if(animeCommentList.isEmpty()){
            responseService.successF(res,"commentaires inexistants", animeCommentList);
            return;
        }
        User currentUser  = Helpers.getCurrentUser(req);
        List<AnimeCommentDTO> animeCommentDTOList =  animeCommentList.stream().map(animeComment -> new AnimeCommentDTO(animeComment, currentUser)).collect(Collectors.toList());

        responseService.successF(res,"le commentaire a été trouvé", animeCommentDTOList);
    }

    @GetMapping()
    public void getAll(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Todo : admin
        List<AnimeComment> animeCommentList = animeCommentService.getAll();
        if (animeCommentList.size() == 0){
            responseService.successF(res,"commentaires inexistants", animeCommentList);
            return;
        }
        User currentUser  = Helpers.getCurrentUser(req);
        List<AnimeCommentDTO> animeCommentDTOList =  animeCommentList.stream().map(animeComment -> new AnimeCommentDTO(animeComment, currentUser)).collect(Collectors.toList());

        responseService.successF(res,"les commentaires ont été trouvés", animeCommentDTOList);
    }

    @PostMapping
    public void createAnimeComments(@RequestBody AnimeCommentDTO animeCommentDTO, HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException, ResourceApiNotFoundException {
        if(animeCommentDTO == null || animeCommentDTO.getIdApiAnime() < 0 || animeCommentDTO.getContenu().isEmpty())
        {
            responseService.errorF(res, Constantes.ErrorMessage.PARAMETER_NOT_EXPECTED,HttpServletResponse.SC_BAD_REQUEST,false);
            return;
        }
        User currentUser  = Helpers.getCurrentUser(req);
        AnimeComment resultAnimeComment =  animeCommentService.createAnimeComment(currentUser,animeCommentDTO);
        if(resultAnimeComment == null){
            responseService.errorF(res, "commentaire animé null",HttpServletResponse.SC_BAD_REQUEST,false);
            return;
        }

        responseService.successF(res,"le commentaire a été ajouté", resultAnimeComment);
    }


    @DeleteMapping("/{idAnimeComment}")
    public void deleteAnimeCommentById(@PathVariable long idAnimeComment,HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException, ResourceApiNotFoundException {
        if (animeCommentService.deleteAnimeCommentById(idAnimeComment)){
            responseService.successF(res,Constantes.SuccessMessage.DELETE_ANIMECOMMENT_OK, true);
        }
    }
}
