package com.jfam.subarashii.entities.dto;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.UserList;

import java.util.List;

public class UserDto {

    private String email;
    private List<UserList> lists;

    public UserDto() {
    }

    public UserDto(User user) {
        this.email = user.getEmail();
        this.lists = user.getLists();
    }

    public User toEntity(){
        User user = new User();
        user.setEmail(this.email);
        user.setLists(this.lists);
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserList> getLists() {
        return lists;
    }

    public void setLists(List<UserList> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", lists=" + lists +
                '}';
    }
}
