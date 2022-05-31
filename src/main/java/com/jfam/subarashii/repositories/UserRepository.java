package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    User findByEmail(String email);

    @Modifying
    @Query(value = "SELECT DISTINCT a.id_api FROM users AS u " +
            "INNER JOIN userlists ul ON ul.user_id = u.id_user " +
            "INNER JOIN userlist_anime ula ON ula.userlist_id = ul.id " +
            "INNER JOIN animes a ON a.id = ula.anime_id " +
            "WHERE u.id_user = :userId",
            nativeQuery = true)
    List<Long> getAllIdApiAnimeOnAllUserList(@Param("userId") Long userId);


    @Query(value = "SELECT COUNT(u) FROM User u " +
            "WHERE u.role = :role")
    Integer countByRole(@Param("role") String role);
}
