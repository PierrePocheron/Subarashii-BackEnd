package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    User findByEmail(String email);
}
