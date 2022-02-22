package com.jfam.subarashii.entities;

import com.github.uzrnem.verify.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_User;

    @Column @NotBlank @NotNull
    private String email;

    @Column @NotBlank @NotNull
    private String password;

    @Column(columnDefinition = "varchar(255) default 'USER'") @NotBlank @NotNull
    private String role = Roles.USER.toString();

    @OneToMany @NotBlank @NotNull
    @JoinColumn( name = "user_id")
    private List<UserLists> lists;

    @OneToMany @NotBlank @NotNull
    @JoinColumn( name = "anime_id")
    private List<UserListsAnimes> animesId;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserLists> getLists() {
        return lists;
    }

    public void setLists(List<UserLists> lists) {
        this.lists = lists;
    }

    public long getId_User() {
        return id_User;
    }

    public void setId_User(long id_User) {
        this.id_User = id_User;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                '}';
    }

    public static Validator<User> validatorSignUp = Validator.stream(User.class)
            .add(User::getEmail, Validator.REQUIRED & Validator.EMAIL, "Un email est requis pour l'inscription")
            .add(User::getPassword, Validator.REQUIRED , "Aucun password n'a été renseigné")
            .min(User::getPassword, 5, "Le password doit contenir au minimum 5 caractères");
}
