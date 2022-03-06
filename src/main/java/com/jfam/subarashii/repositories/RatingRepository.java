package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
