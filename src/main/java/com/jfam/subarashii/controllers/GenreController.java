package com.jfam.subarashii.controllers;

import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    ResponseService responseService;


    @GetMapping("/all")
    public void GetAllGenres(HttpServletResponse res) throws IOException {
        responseService.SuccessF(res, Constantes.SuccessMessage.GENRE_HAS_FETCH, true);
    }
}
