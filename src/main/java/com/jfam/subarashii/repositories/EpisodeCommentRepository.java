package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.EpisodeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeCommentRepository extends JpaRepository<AnimeComment,Long> {
    List<EpisodeComment> findAllByAnime_IdApi(Long idApi);
}
