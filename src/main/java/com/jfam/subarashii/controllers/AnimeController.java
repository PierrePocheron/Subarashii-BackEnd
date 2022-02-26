package com.jfam.subarashii.controllers;

import com.google.gson.internal.LinkedTreeMap;
import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.services.AnimeService;
import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/animes")
public class AnimeController {

    @Autowired
    AnimeService animeService;

    @Autowired
    ResponseService responseService;


    @GetMapping
    public void GetOne(HttpServletResponse res) throws IOException {
        Anime anime = animeService.getOne();
        if(anime == null){
            responseService.ErrorF(res, Constantes.ErrorMessage.ANIME_NOT_FOUND,HttpServletResponse.SC_NOT_FOUND, null);
            return;
        }
        responseService.SuccessF(res,"Un animé à été trouvé", anime);
    }

    @PreAuthorize("#req.isUserInRole('ADMIN')")
    @GetMapping(path = "/all")
    public void GetAll(HttpServletRequest req,HttpServletResponse res) throws IOException {
        List<Anime> animeList = animeService.getAll();

        if(animeList == null || animeList.size() == 0){
            responseService.ErrorF(res, Constantes.ErrorMessage.ANIME_NOT_FOUND,HttpServletResponse.SC_NOT_FOUND, null);
            return;
        }

        responseService.SuccessF(res,"Des animés ont été trouvés", animeList);
    }

    @GetMapping("/{id}")
    public void GetById(@PathVariable long id,HttpServletResponse res) throws IOException{
        Anime result = animeService.getById(id);
        responseService.SuccessF(res,"Des animés ont été trouvés", result);
    }
}
