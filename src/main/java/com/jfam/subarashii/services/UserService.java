package com.jfam.subarashii.services;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserListService userListService;

    public User login(User user) {
        User userFetching = userRepository.findByEmail(user.getEmail());
        if (userFetching == null)
            return null;

        boolean passOK = passwordEncoder.matches(user.getPassword(), userFetching.getPassword());
        return passOK ? userFetching : null;
    }

    public User create(User user) {
        User usr = userRepository.save(user);
        userListService.createDefaultList(usr);
        return usr;
    }

    public User getUserForRequestByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(user == null)
            return null;
        user.setPassword(null);
        return user;
    }
}

