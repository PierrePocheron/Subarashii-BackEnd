package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime,Long> {
    Anime findByIdApi(Long idApi);
}
