package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "secretquestions")
public class SecretQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSecretQuestion;

    @Column(unique = true) @NotNull
    private String question;

    @OneToMany(mappedBy = "secretQuestion")
    private List<User> listUsers;

    public SecretQuestion(long idSecretQuestion) {
        this.idSecretQuestion = idSecretQuestion;
    }

    public SecretQuestion() {

    }

    public long getIdSecretQuestion() {
        return idSecretQuestion;
    }

    public void setIdSecretQuestion(long idSecretQuestion) {
        this.idSecretQuestion = idSecretQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
