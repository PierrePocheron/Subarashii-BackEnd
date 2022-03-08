package com.jfam.subarashii.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AnimeComments")
public class AnimeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @NotNull
    private String contenu;

    //rajouter idUser
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "IdApiAnime")
    private Anime anime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AnimeComment{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", user=" + user +
                ", anime=" + anime +
                '}';
    }
}
