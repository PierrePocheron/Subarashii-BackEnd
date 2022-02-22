package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserListRepository extends JpaRepository<UserList,Long> {
}
