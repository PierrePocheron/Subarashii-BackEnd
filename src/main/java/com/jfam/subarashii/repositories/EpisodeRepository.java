package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode,Long> {
}
