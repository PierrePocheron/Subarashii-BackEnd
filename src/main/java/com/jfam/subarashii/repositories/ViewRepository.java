package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewRepository extends JpaRepository<View,Long> {
    View findByIdApiAnimeAndIdApiEpisodeAndUser(Long IdApiAnime,Long IdApiEpisode, User user);
    List<View> findAllByUserAndIdApiAnimeAndSeeIsTrue(User user, Long IdApiAnime);
    void deleteById(Long idView);
}
