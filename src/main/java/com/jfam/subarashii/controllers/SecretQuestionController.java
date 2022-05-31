package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.SecretQuestionService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/secretquestions")
@Tag(name = "SecretQuestions")
public class SecretQuestionController {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private SecretQuestionService secretQuestionService;

    @GetMapping
    public void getAllSecretQuestions(HttpServletResponse res) throws IOException {
        List<SecretQuestion> secretQuestionList = secretQuestionService.getAll();
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_ALL_SECRET_QUESTIONS,secretQuestionList.size()), secretQuestionList);
    }
}