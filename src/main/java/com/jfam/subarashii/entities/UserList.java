package com.jfam.subarashii.entities;

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
    private boolean isDeletabled = true;

    @OneToMany @NotNull
    @JoinColumn( name = "userListId")
    private List<UserListAnime> userListsId;

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
        return isDeletabled;
    }

    public void setDeletable(boolean deletable) {
        isDeletabled = deletable;
    }

    public boolean isDeletabled() {
        return isDeletabled;
    }

    public void setDeletabled(boolean deletabled) {
        isDeletabled = deletabled;
    }

    public List<UserListAnime> getUserListsId() {
        return userListsId;
    }

    public void setUserListsId(List<UserListAnime> userListsId) {
        this.userListsId = userListsId;
    }

    //endregion
}
