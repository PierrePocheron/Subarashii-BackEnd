package com.jfam.subarashii.services;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.*;
import com.jfam.subarashii.repositories.UserRepository;
import com.jfam.subarashii.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public User create(User userToCreate) {
        User usr = userRepository.save(userToCreate);
        List<UserList> userListList = userListService.createDefaultList(usr);
        usr.setLists(userListList);
        User usrT = userRepository.save(userToCreate);
        return usrT;
    }

    public User create(User userToCreate, SecretQuestion secretQuestion) {
        userToCreate.setSecretQuestion(secretQuestion);
        User usr = userRepository.save(userToCreate);
        List<UserList> userListList = userListService.createDefaultList(usr);
        usr.setLists(userListList);
        User usrT = userRepository.save(userToCreate);
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

    public User getByIdUser(long idUser) throws ResponseStatusException {
        Optional<User> userOpt = userRepository.findById(idUser);

        if (userOpt == null)
            return null;

        User user = userOpt.get();
        return user;
    }

    public List<User> getAll() {
        List<User> userList = userRepository.findAll();
        return userList;
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

    public User patchRoleUserGrantAdmin(User currentUser, Long idUser) throws  ResponseStatusException {
        if (currentUser.getRole().equals(Role.ADMIN.toString())) {
            Optional<User> userOpt = userRepository.findById(idUser);
            if(userOpt.isPresent()) {
                User userTemp = userOpt.get();
                userTemp.setRole(Role.ADMIN.toString());

                return userRepository.save(userTemp);
            } else {
                //User doesn't exist -> throw exception
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_EXISTS);
            }
        } else {
            //User doesn't admin -> throw exception
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_RIGHTS_ADMIN);
        }
    }

    public User patchRoleUserGrantUser(User currentUser, Long idUser) throws  ResponseStatusException {
        if (currentUser.getRole().equals(Role.ADMIN.toString())) {
            Optional<User> userOpt = userRepository.findById(idUser);
            if(userOpt.isPresent()) {
                User userTemp = userOpt.get();
                userTemp.setRole(Role.USER.toString());

                return userRepository.save(userTemp);
            } else {
                //User doesn't exist -> throw exception
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_EXISTS);
            }
        } else {
            //User doesn't admin -> throw exception
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_RIGHTS_ADMIN);
        }
    }

    public boolean deleteUserById(User currentUser, Long idUser) {
        if (currentUser.getRole().equals(Role.ADMIN.toString())) {
            Optional<User> userOpt = userRepository.findById(idUser);
            if(userOpt.isPresent()) {
                User user = userOpt.get();
                userRepository.delete(user);
                return true;
            } else {
                //User doesn't exist -> throw exception
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_EXISTS);
            }
        } else {
            //User doesn't admin -> throw exception
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, Constantes.ErrorMessage.EXCEPTION_USER_DOESNT_RIGHTS_ADMIN);
        }
    }
}

