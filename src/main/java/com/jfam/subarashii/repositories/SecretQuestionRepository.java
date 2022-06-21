package com.jfam.subarashii.repositories;

import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretQuestionRepository extends JpaRepository<SecretQuestion,Long> {
SecretQuestion findByIdSecretQuestion(String question);
}
