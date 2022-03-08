package com.jfam.subarashii.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EpisodeComments")
public class EpisodeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @NotNull
    private String contenu;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "IdApiEpisode")
    private Episode episode;


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

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "EpisodeComment{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", episode=" + episode +
                '}';
    }
}
