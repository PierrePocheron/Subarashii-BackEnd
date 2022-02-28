package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository  extends JpaRepository<Genre,Long> {

    Genre findByIdApi(Long idApi);

    List<Genre> findByIdApiIn(List<Long> idApiList);
}
