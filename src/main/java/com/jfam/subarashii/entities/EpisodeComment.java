package com.jfam.subarashii.entities;

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


    @Column @NotNull
    private String statut;

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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }


    @Override
    public String toString() {
        return "EpisodeComment{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
