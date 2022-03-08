package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    User findByEmail(String email);

    @Query(value = "SELECT DISTINCT a.id_api FROM USERS AS u INNER JOIN userlists ul ON ul.user_id = u.id_user INNER JOIN userlist_anime ula ON ula.userlist_id = ul.id INNER JOIN animes a ON a.id = ula.anime_id WHERE u.id_user = :userId", nativeQuery = true)
    List<Long> getAllIdApiAnimeOnAllUserList(@Param("userId") Long userId);
}
