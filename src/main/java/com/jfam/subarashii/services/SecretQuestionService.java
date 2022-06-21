package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.repositories.SecretQuestionRepository;
import com.jfam.subarashii.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecretQuestionService {

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


}
