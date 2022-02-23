package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
