package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserListRepository extends JpaRepository<UserList,Long> {

    List<UserList> findAllByUser(User user);
    UserList findByIdAndUser(Long id, User user);
    UserList findAllByUserAndId(User user, Long id);

    @Query(value = "SELECT count(*) FROM userlist_anime ula " +
            "INNER JOIN userlists ul on ula.userlist_id = ul.id " +
            "WHERE ula.userlist_id = " +
            "      (SELECT id FROM userlists ul2 " +
            "          WHERE ul2.user_id = :idUser " +
            "          AND ul2.nom = :status)", nativeQuery = true)
    Long getCurrentUserCountAnimeByStatus(@Param("status")String status, @Param("idUser")Long idUser);
}
