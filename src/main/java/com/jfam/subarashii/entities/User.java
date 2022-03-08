package com.jfam.subarashii.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.uzrnem.verify.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(unique = true) @NotNull
    private String email;

    @Column @NotNull
    private String password;

    @Column(columnDefinition = "varchar(255) default 'USER'") @NotNull
    private String role = Role.USER.toString();

    @OneToMany(mappedBy = "user")
    private List<UserList> lists;


    @OneToMany(mappedBy = "user")
    private List<View> views;

    @NotNull
    private String username;

    public User(){}

    public User(String name, String role){
        this.email = name;
        this.role = role;
    }


    public static Validator<User> validatorSignUp = Validator.stream(User.class)
            .add(User::getEmail, Validator.REQUIRED | Validator.EMAIL, "Un email est requis pour l'inscription")
            .add(User::getPassword, Validator.REQUIRED , "Aucun password n'a été renseigné")
            .min(User::getPassword, 5, "Le password doit contenir au minimum 5 caractères");

    //region  === getter-setter ===

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserList> getLists() {
        return lists;
    }

    public void setLists(List<UserList> lists) {
        this.lists = lists;
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

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//endregion

}
