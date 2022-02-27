package com.jfam.subarashii.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "userlists")
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NotNull
    private String nom;

    @Column @NotNull
    private boolean isDeletable = true;

    @ManyToOne
    @JoinColumn( name = "userId")
    @JsonBackReference
    private User user;

    public UserList(){}


    public UserList(String nom , User user , Boolean isDeletable){
        this.nom = nom;
        this.user = user;
        this.isDeletable = isDeletable == null ? true : isDeletable ;
    }


    //region  === getter-setter ===

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
        return isDeletable;
    }

    public void setDeletable(boolean deletable) {
        isDeletable = deletable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//endregion
}
