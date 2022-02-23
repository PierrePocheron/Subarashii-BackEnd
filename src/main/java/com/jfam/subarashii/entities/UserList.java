package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "userlists")
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NotBlank @NotNull
    private String nom;

    @Column @NotBlank @NotNull
    private boolean isDeletabled = true;

    @OneToMany @NotBlank @NotNull
    @JoinColumn( name = "userList_id")
    private List<UserListAnime> userLists_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isDeletable() {
        return isDeletabled;
    }

    public void setDeletable(boolean deletable) {
        isDeletabled = deletable;
    }

    

    public List<UserListAnime> getUserLists_id() {
        return userLists_id;
    }

    public void setUserLists_id(List<UserListAnime> userLists_id) {
        this.userLists_id = userLists_id;
    }
}
