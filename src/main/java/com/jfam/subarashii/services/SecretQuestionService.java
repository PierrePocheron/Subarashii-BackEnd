package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.repositories.SecretQuestionRepository;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SecretQuestionService {

    @Autowired
    private SecretQuestionRepository secretQuestionRepository;

    public List<SecretQuestion> getAll() {
        List<SecretQuestion> secretQuestionList = secretQuestionRepository.findAll();
        return secretQuestionList;
    }


    public SecretQuestion createSecretQuestion(SecretQuestion secretQuestion) throws ResponseStatusException {
        // Save SecretQuestion
        return secretQuestionRepository.save(secretQuestion);
    }


    public SecretQuestion getSecretQuestionById(Long idSecretQuestion) throws ResponseStatusException {
        Optional<SecretQuestion> secretQuestionOpt = secretQuestionRepository.findById(idSecretQuestion);
        if(secretQuestionOpt.isPresent()) {
            //SecretQuestion exist -> get it
            SecretQuestion secretQuestion = secretQuestionOpt.get();
            return secretQuestion;
        }
        else {
            //SecretQuestion doesn't exist -> throw exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_SECRET_QUESTION_DOESNT_EXISTS);
        }
    }


    public List<SecretQuestion> getAllSecretQuestions() {
        return secretQuestionRepository.findAll();
    }


    public SecretQuestion patchSecretQuestionById(SecretQuestion secretQuestion, Long idSecretQuestion) throws ResponseStatusException{
        Optional<SecretQuestion> secretQuestionOpt = secretQuestionRepository.findById(idSecretQuestion);
        if(secretQuestionOpt.isPresent()) {
            SecretQuestion secretQuestionTemp = secretQuestionOpt.get();
            secretQuestionTemp.setIdSecretQuestion(idSecretQuestion);

            if (secretQuestion.getQuestion() != null){
                secretQuestionTemp.setQuestion(secretQuestion.getQuestion());
            }
            return secretQuestionRepository.save(secretQuestionTemp);
        } else {
            //SecretQuestion doesn't exist -> throw exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_SECRET_QUESTION_DOESNT_EXISTS);
        }
    }

    public boolean deleteSecretQuestionById(Long idSecretQuestion) throws ResponseStatusException {
        Optional<SecretQuestion> secretQuestionOpt = secretQuestionRepository.findById(idSecretQuestion);
        if(secretQuestionOpt.isPresent()) {
            //SecretQuestion exist -> delete it
            SecretQuestion secretQuestion = secretQuestionOpt.get();
            secretQuestionRepository.delete(secretQuestion);
            return true;
        } else {
            //SecretQuestion doesn't exist -> throw exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_SECRET_QUESTION_DOESNT_EXISTS);
        }
    }
}
