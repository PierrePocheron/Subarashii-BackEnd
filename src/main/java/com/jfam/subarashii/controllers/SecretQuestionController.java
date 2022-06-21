package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.entities.User;

import com.jfam.subarashii.repositories.UserRepository;

import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.SecretQuestionService;
import com.jfam.subarashii.services.UserListService;
import com.jfam.subarashii.services.UserService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
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
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    UserListService userListService;

    @Autowired
    private SecretQuestionService secretQuestionService;

    @GetMapping(path = "/all")
    public void getAllSecretQuestions(HttpServletResponse res) throws IOException {
        List<SecretQuestion> secretQuestionList = secretQuestionService.getAll();
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_ALL_SECRET_QUESTIONS,secretQuestionList.size()), secretQuestionList);
    }


    @PostMapping(value = "/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendSecretQuestion( HttpServletResponse res,Long idUser) throws IOException {
        SecretQuestion questionSecrete = secretQuestionService.sendSecretQuestion(idUser);
        responseService.successF(res, Constantes.SuccessMessage.QUESTION_SECRETE_FOUND, questionSecrete);
    }

    @PostMapping(value = "/{idUser}/answer-secret-question", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendAnswerSecretQuestion(HttpServletResponse res, Long idUser) throws IOException {
        String answerQuestionSecrete = secretQuestionService.sendAnswerSecretQuestion(idUser);
        responseService.successF(res, Constantes.SuccessMessage.QUESTION_SECRETE_FOUND, answerQuestionSecrete);
    }




    @PostMapping
    public void createSecretQuestion(HttpServletRequest req,
                                     HttpServletResponse res,
                                     @RequestBody SecretQuestion secretQuestion) throws IOException{
        User currentUser = Helpers.getCurrentUser(req);

        SecretQuestion secretQuestionCreated = secretQuestionService.createSecretQuestion(currentUser, secretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.CREATE_SECRET_QUESTION, secretQuestionCreated);
    }


    @GetMapping(path = "/{idSecretQuestion}")
    public void getSecretQuestion(HttpServletResponse res,
                                  @PathVariable(name = "idSecretQuestion") Long idSecretQuestion) throws IOException{
        SecretQuestion secretQuestion = secretQuestionService.getSecretQuestionById(idSecretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.GET_SECRET_QUESTION, secretQuestion);
    }


    @PatchMapping(path = "/{idSecretQuestion}")
    public void patchSecretQuestion(HttpServletRequest req,
                                    HttpServletResponse res,
                                    @RequestBody SecretQuestion secretQuestion,
                                    @PathVariable(value = "idSecretQuestion") Long idSecretQuestion) throws IOException{
        User currentUser = Helpers.getCurrentUser(req);

        SecretQuestion secretQuestionUpdated = secretQuestionService.patchSecretQuestionById(currentUser, secretQuestion, idSecretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.PATCH_SECRET_QUESTION, secretQuestionUpdated);
    }


    @DeleteMapping(path = "/{idSecretQuestion}")
    public void deleteSecretQuestion(HttpServletRequest req,
                                     HttpServletResponse res,
                                     @PathVariable(value = "idSecretQuestion") Long idSecretQuestion) throws IOException{
        User currentUser = Helpers.getCurrentUser(req);

        Boolean isDeleted = secretQuestionService.deleteSecretQuestionById(currentUser, idSecretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.DELETE_SECRET_QUESTION, isDeleted);
    }

}