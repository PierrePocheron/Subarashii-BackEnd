package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserListRepository extends JpaRepository<UserList,Long> {

    List<UserList> findAllByUser(User user);
}
