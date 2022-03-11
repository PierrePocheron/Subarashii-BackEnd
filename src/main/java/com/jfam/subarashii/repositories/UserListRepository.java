package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.From;
import java.util.List;

public interface UserListRepository extends JpaRepository<UserList,Long> {

    List<UserList> findAllByUser(User user);
    UserList findByIdAndUser(Long id, User user);
    UserList findAllByUserAndId(User user, Long id);



}
