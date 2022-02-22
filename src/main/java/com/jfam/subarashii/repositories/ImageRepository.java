package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
