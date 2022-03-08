package com.jfam.subarashii.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "views",uniqueConstraints=
@UniqueConstraint(columnNames = {"user_id", "idApiEpisode","idApiAnime"}))
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "user_id" )
    @JsonBackReference
    private User user;

    private Long idApiEpisode;

    private Long idApiAnime;

    private boolean see = true;
    //#region === GETTER - SETTER

    public Long getId() {
        return id;
    }

    public Long getIdApiAnime() {
        return idApiAnime;
    }

    public void setIdApiAnime(Long idApiAnime) {
        this.idApiAnime = idApiAnime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdApiEpisode() {
        return idApiEpisode;
    }

    public void setIdApiEpisode(Long idApiEpisode) {
        this.idApiEpisode = idApiEpisode;
    }

    public boolean isSee() {
        return see;
    }

    public void setSee(boolean see) {
        this.see = see;
    }

    //endregion
}
