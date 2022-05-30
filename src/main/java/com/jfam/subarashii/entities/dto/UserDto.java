package com.jfam.subarashii.entities.dto;

import com.jfam.subarashii.entities.User;


public class UserDto {

    private String email;



    public UserDto() {
    }

    public UserDto(User user) {
        this.email = user.getEmail();

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
}
