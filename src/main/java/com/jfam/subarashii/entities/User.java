package com.jfam.subarashii.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.uzrnem.verify.Validator;
import com.google.gson.JsonObject;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference(value="user-anime")
    private List<AnimeComment> animeComments;


    @NotNull
    private String username;

    @ManyToOne
    //@NotNull
    @JoinColumn(name = "secretQuestionId")
    @JsonBackReference
    private SecretQuestion secretQuestion;

    @Column
    private String answerSecretQuestion;




    public User(){}

    public User(String name, String role){
        this.email = name;
        this.role = role;
    }

    public User(long idUser, String email, String role, String username) {
        this.idUser = idUser;
        this.email = email;
        this.role = role;
        this.username = username;
    }

    public User(long idUser, String email, String password, String role, String username, SecretQuestion secretQuestion, String answerSecretQuestion) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.secretQuestion = secretQuestion;
        this.answerSecretQuestion = answerSecretQuestion;
    }


    public User(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.username = user.getUsername();
        this.answerSecretQuestion = user.getAnswerSecretQuestion();
        this.secretQuestion.setIdSecretQuestion(user.getSecretQuestion().getIdSecretQuestion());
    }

    public User(User user, SecretQuestion secretQuestion){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.username = user.getUsername();
        this.answerSecretQuestion = user.getAnswerSecretQuestion();
        this.secretQuestion = secretQuestion;
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

    public List<AnimeComment> getAnimeComments() {
        return animeComments;
    }

    public void setAnimeComments(List<AnimeComment> animeComments) {
        this.animeComments = animeComments;
    }

    public SecretQuestion getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(SecretQuestion secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getAnswerSecretQuestion() {
        return answerSecretQuestion;
    }

    public void setAnswerSecretQuestion(String answerSecretQuestion) {
        this.answerSecretQuestion = answerSecretQuestion;
    }

    //endregion

}
