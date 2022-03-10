package com.jfam.subarashii.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "userlists")
@SecurityRequirement(name = "javainuseapi")
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @NotNull
    private String nom;

    @Column @NotNull
    private boolean isDeletable = true;

    @ManyToOne
    @JoinColumn( name = "userId")
    @JsonBackReference
    private User user;


    @ManyToMany
    @JoinTable(name = "userlist_anime",
            joinColumns = @JoinColumn(name = "userlistId"),
            inverseJoinColumns = @JoinColumn(name = "animeId")
    )
    private List<Anime> animes;

    public UserList(){}

    public UserList(String nom , User user , Boolean isDeletable){
        this.nom = nom;
        this.user = user;
        this.isDeletable = isDeletable == null ? true : isDeletable ;
    }



    //region  === getter-setter ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }


    //endregion
}
