package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Genre;
import com.jfam.subarashii.services.GenreService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    ResponseService responseService;

    @Autowired
    GenreService genreService;



    @GetMapping("/all")
    public void getAllGenres(HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        List<Genre> genreList = genreService.getAll();
        responseService.successF(res, Constantes.SuccessMessage.GENRE_HAS_FETCH, genreList);
    }

    @GetMapping("/{idapi}")
    public void getGenreById(@PathVariable long idapi, HttpServletResponse res) throws IOException {
        Genre genre = genreService.getGenreByIdApi(idapi);
        if(genre == null) {
            responseService.errorF(res, Constantes.ErrorMessage.GENRE_NOT_FOUND, HttpServletResponse.SC_NOT_FOUND, false);
            return;
        }

        responseService.successF(res, Constantes.SuccessMessage.GENRE_HAS_FETCH, genre);
    }
}
