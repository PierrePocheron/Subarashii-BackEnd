package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.AnimeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeCommentRepository extends JpaRepository<AnimeComment,Long> {
}
