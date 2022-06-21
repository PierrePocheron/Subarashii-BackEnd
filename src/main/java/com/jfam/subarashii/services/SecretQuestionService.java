package com.jfam.subarashii.services;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.repositories.SecretQuestionRepository;

import com.jfam.subarashii.repositories.UserRepository;

import com.jfam.subarashii.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SecretQuestionService {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private SecretQuestionRepository secretQuestionRepository;

    @Autowired
    UserRepository userRepository;

    public List<SecretQuestion> getAll() {
        List<SecretQuestion> secretQuestionList = secretQuestionRepository.findAll();
        return secretQuestionList;
    }


    public SecretQuestion sendSecretQuestion(Long idUser){
        // recupère l'idUser
        Optional<User> userTemp = userRepository.findById(idUser);

        //recupérer la question secrète du user
       return userTemp.get().getSecretQuestion();


    }

    public String sendAnswerSecretQuestion(Long idUser){
        // recupère l'idUser
        Optional<User> userTemp = userRepository.findById(idUser);

        //recupérer la question secrète du user
        return userTemp.get().getAnswerSecretQuestion();


    }




    public SecretQuestion createSecretQuestion(User currentUser, SecretQuestion secretQuestion) throws ResponseStatusException {
        if (currentUser.getRole().equals(Role.ADMIN.toString())) {
            // Save SecretQuestion
            return secretQuestionRepository.save(secretQuestion);

        } else {
            //User doesn't admin -> throw exception
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_RIGHTS_ADMIN);
        }
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


    public SecretQuestion patchSecretQuestionById(User currentUser, SecretQuestion secretQuestion, Long idSecretQuestion) throws ResponseStatusException{
        if (currentUser.getRole().equals(Role.ADMIN.toString())) {
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
        } else {
            //User doesn't admin -> throw exception
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_RIGHTS_ADMIN);
        }
    }

    public boolean deleteSecretQuestionById(User currentUser, Long idSecretQuestion) throws ResponseStatusException {
        if (currentUser.getRole().equals(Role.ADMIN.toString())) {
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
        } else {
            //User doesn't admin -> throw exception
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_RIGHTS_ADMIN);
        }
    }

}
