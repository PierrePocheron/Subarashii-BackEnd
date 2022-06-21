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
import java.util.List;
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

    /**
     * @description get a valid user
     *
     * @assert create user entity & persist
     * @when find user by his email
     * @then user found is not empty & user fond's email equals to user's email
     */
    @Test
    @Order(2)
    public void getUserByEmailSuccess() {
        final SecretQuestion secretQuestion = new SecretQuestion(1, "Quel est ton animal préferé ?");
        final User user = new User(1, "pierre.pocheron@gmail.com", "unPassword", Role.USER.toString(), "Pierre", secretQuestion, "chat");

        entityManager.persist(user);
        entityManager.flush();

        User userFound = userRepository.findByEmail(user.getEmail());

        assertThat(userFound).isNotNull(); //Should be null
        assertThat(userFound.getEmail()).isEqualTo(user.getEmail()); //Should be equals
    }

    /**
     * @description get a user by wrong email
     *
     * @assert create user entity & persist
     * @when find user by wrong email
     * @then user found is empty
     */
    @Test
    @Order(3)
    public void getUserByEmailFailed() {
        final SecretQuestion secretQuestion = new SecretQuestion(1, "Quel est ton animal préferé ?");
        final User user = new User(1, "pierre.pocheron@gmail.com", "unPassword", Role.USER.toString(), "Pierre", secretQuestion, "chat");

        entityManager.persist(user);
        entityManager.flush();

        User userFound = userRepository.findByEmail("other.email@gmail.com");

        assertThat(userFound).isNotNull(); //Should be null
    }


    /**
     * @description get all users
     *
     * @assert create 3 users entities & persist
     * @when find all users registers
     * @then list users found contains 3 users
     */
    @Test
    @Order(4)
    public void getAllUsers(){
        final SecretQuestion secretQuestion = new SecretQuestion(1, "Quel est ton animal préferé ?");
        final User user1 = new User(1, "pierre.pocheron1@gmail.com", "unPassword1", Role.USER.toString(), "Pierre1", secretQuestion, "chat");
        final User user2 = new User(2, "pierre.pocheron2@gmail.com", "unPassword2", Role.ADMIN.toString(), "Pierre2", secretQuestion, "chien");
        final User user3 = new User(3, "pierre.pocheron3@gmail.com", "unPassword3", Role.USER.toString(), "Pierre3", secretQuestion, "souris");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.flush();

        List<User> listUsersFound = userRepository.findAll();

        assertThat(listUsersFound).size().isEqualTo(3);
    }


    /**
     * @description Update user
     *
     * @given create user entity & persist > update Firstname & Lastname > save user
     * @when find user updated
     * @then user updated equals user to update
     */
    @Test
    @Order(5)
    public void updateUserSuccess(){
        final SecretQuestion secretQuestion = new SecretQuestion(1, "Quel est ton animal préferé ?");
        final User user = new User(1, "pierre.pocheron@gmail.com", "unPassword", Role.USER.toString(), "Pierre", secretQuestion, "chat");

        entityManager.persist(user);
        entityManager.flush();

        User userToUpdate = userRepository.findByEmail("pierre.pocheron@gmail.com");
        userToUpdate.setUsername("New username");

        User userUpdated = userRepository.save(userToUpdate);

        assertThat(userUpdated).isEqualTo(userToUpdate);
    }


    /**
     * @description delete user
     *
     * @given create user entity & persist
     * @when delete user > find user by his email
     * @then user found is empty
     */
    @Test
    @Order(6)
    public void deleteUserSuccess() {
        final SecretQuestion secretQuestion = new SecretQuestion(1, "Quel est ton animal préferé ?");
        final User user = new User(1, "pierre.pocheron@gmail.com", "unPassword", Role.USER.toString(), "Pierre", secretQuestion, "chat");

        entityManager.persist(user);
        entityManager.flush();

        userRepository.delete(user);
        User userFound = userRepository.findByEmail("pierre.pocheron@gmail.com");

        assertThat(userFound).isNotNull();
    }
}
