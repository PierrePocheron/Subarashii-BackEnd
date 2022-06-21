package com.jfam.subarashii.utils.repositories;

import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.repositories.UserRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    /**
     * @description Create a valid user
     *
     * @assert create user entity & persist
     * @when find user by his email
     * @then user.getId() > 0
     */
    @Test
    @Order(1)
    public void createUserSuccess() {
        final SecretQuestion secretQuestion = new SecretQuestion(1, "Quel est ton animal préferé ?");
        final User user = new User(1, "pierre.pocheron@gmail.com", "unPassword", Role.USER.toString(), "Pierre", secretQuestion, "chat");
        entityManager.persist(user);
        entityManager.flush();

        User userFound = userRepository.findByEmail(user.getEmail());

        assertThat(userFound.getIdUser()).isGreaterThan(0);
    }
}
