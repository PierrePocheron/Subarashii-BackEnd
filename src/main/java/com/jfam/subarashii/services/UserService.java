package com.jfam.subarashii.services;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    UserRepository userRepository;

    public User login(User user){
        if(user == null){
            return null ;
        }
        logger.info("Tentative de login sur l'utilisateur: " + user.toString());
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
