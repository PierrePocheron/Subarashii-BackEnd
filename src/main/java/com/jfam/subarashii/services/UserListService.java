package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import com.jfam.subarashii.repositories.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserListService {

    @Autowired
    UserListRepository userListRepository;

    public List<UserList> createDefaultList(User user){

        List<UserList> defaultList = Arrays.asList (
            new UserList("A voir", user, false),
            new UserList("En cour", user, false),
            new UserList("Terminer", user, false),
            new UserList("En attente", user, false)
        );

        return userListRepository.saveAll(defaultList);
    }

    public UserList createCustomList(User user , String nameList){
        UserList customList = new UserList(nameList, user,true);
        return userListRepository.save(customList);
    }

    public List<UserList> getCurrentUserList(User user){
        List<UserList> userList = userListRepository.findAllByUser(user);
        return userList;
    }
}
