package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_User;

    @Column @NotBlank
    private String email;

    @Column @NotBlank
    private String password;

    @Column @NotBlank
    private String role = Roles.USER.toString();

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

    @OneToMany
    @JoinColumn( name = "user_id")
    private List<UserLists> lists;

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
}
