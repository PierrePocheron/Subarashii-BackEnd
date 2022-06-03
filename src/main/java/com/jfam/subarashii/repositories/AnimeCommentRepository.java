package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.AnimeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimeCommentRepository extends JpaRepository<AnimeComment,Long> {
    List<AnimeComment > findAllByAnimeIdApi(Long idApi);


    @Query(value = "SELECT count(ac) FROM AnimeComment ac " +
            "WHERE ac.user.idUser = :idUser")
    Long getCurrentUserCountComments(@Param("idUser")Long idUser);
}
