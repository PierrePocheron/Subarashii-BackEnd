package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.SecretQuestionService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public void createSecretQuestion(HttpServletResponse res,
                                     @RequestBody SecretQuestion secretQuestion) throws IOException{
        SecretQuestion secretQuestionCreated = secretQuestionService.createSecretQuestion(secretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.CREATE_SECRET_QUESTION, secretQuestionCreated);
    }


    @GetMapping(path = "/{idSecretQuestion}")
    public void getSecretQuestion(HttpServletResponse res,
                                  @PathVariable(name = "idSecretQuestion") Long idSecretQuestion) throws IOException{
        SecretQuestion secretQuestion = secretQuestionService.getSecretQuestionById(idSecretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.GET_SECRET_QUESTION, secretQuestion);
    }


    @PatchMapping(path = "/{idSecretQuestion}")
    public void patchSecretQuestion(HttpServletResponse res,
                                    @RequestBody SecretQuestion secretQuestion,
                                    @PathVariable(value = "idSecretQuestion") Long idSecretQuestion) throws IOException{
        SecretQuestion secretQuestionUpdated = secretQuestionService.patchSecretQuestionById(secretQuestion, idSecretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.PATCH_SECRET_QUESTION, secretQuestionUpdated);
    }


    @DeleteMapping(path = "/{idSecretQuestion}")
    public void deleteSecretQuestion(HttpServletResponse res,
                                     @PathVariable(value = "idSecretQuestion") Long idSecretQuestion) throws IOException{
        Boolean isDeleted = secretQuestionService.deleteSecretQuestionById(idSecretQuestion);
        responseService.successF(res, Constantes.SuccessMessage.DELETE_SECRET_QUESTION, isDeleted);
    }
}