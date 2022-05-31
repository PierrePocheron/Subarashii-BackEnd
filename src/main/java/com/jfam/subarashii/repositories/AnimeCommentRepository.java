package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.AnimeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeCommentRepository extends JpaRepository<AnimeComment,Long> {
    List<AnimeComment > findAllByAnimeIdApi(Long idApi);
}
