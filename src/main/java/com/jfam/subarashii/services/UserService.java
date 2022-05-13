package com.jfam.subarashii.services;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import com.jfam.subarashii.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        List<UserList> userListList = userListService.createDefaultList(usr);
        usr.setLists(userListList);
        User usrT = userRepository.save(user);
        return usrT;
    }

    public User getUserForFilterByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(user == null)
            return null;
        user.setPassword(null);
        return user;
    }

    public List<Long> getAllIdApiAnimeOnUserList(User user){
        return userRepository.getAllIdApiAnimeOnAllUserList(user.getIdUser());
    }


    // Todo : add javadocs
    public User getByIdUser(long idUser) throws ResponseStatusException {
        Optional<User> userOpt = userRepository.findById(idUser);

        if (userOpt == null)
            return null;

        User user = userOpt.get();
        return user;
    }

    public User patchUsernameUserConnected(User user) throws ResponseStatusException {
        Optional<User> userOpt = userRepository.findById(user.getIdUser());

        if (userOpt == null)
            return null;

        User userToPatch = userOpt.get();
        userToPatch.setUsername(user.getUsername());
        User userPatched = userRepository.save(userToPatch);

        return userPatched;
    }

    public User patchPasswordUserConnected(User user) throws ResponseStatusException {
        Optional<User> userOpt = userRepository.findById(user.getIdUser());

        if (userOpt == null)
            return null;

        User userToPatch = userOpt.get();
        userToPatch.setPassword(user.getPassword());
        User userPatched = userRepository.save(userToPatch);

        return userPatched;
    }
}

