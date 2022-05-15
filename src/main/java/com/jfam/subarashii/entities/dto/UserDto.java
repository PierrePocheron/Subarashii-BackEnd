package com.jfam.subarashii.entities.dto;

import com.jfam.subarashii.entities.User;


public class UserDto {

    private String email;
    private SecretQuestionDTO secretQuestion;
    private String answerSecretQuestion;


    public UserDto() {
    }

    public UserDto(User user) {
        this.email = user.getEmail();

    }

    public UserDto(String email, SecretQuestionDTO secretQuestion, String answerSecretQuestion) {
        this.email = email;
        this.secretQuestion = secretQuestion;
        this.answerSecretQuestion = answerSecretQuestion;
    }

    public User toEntity(){
        User user = new User();
        user.setEmail(this.email);

        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SecretQuestionDTO getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(SecretQuestionDTO secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getAnswerSecretQuestion() {
        return answerSecretQuestion;
    }

    public void setAnswerSecretQuestion(String answerSecretQuestion) {
        this.answerSecretQuestion = answerSecretQuestion;
    }
}
