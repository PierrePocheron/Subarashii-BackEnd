package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode,Long> {
    List<Episode> findAllByAnimeIdApiAndSaison ( Long idAnime,Long idsaison);
}
