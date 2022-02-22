package com.jfam.subarashii.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "animes")
public class Anime {

    @Id
    @GeneratedValue
    private long id;

    private String nom;

    private String alias;

    private String producer;

    private String image;

    @OneToMany
    @JoinColumn( name = "anime_id")
    private List<Comments> anime_id;


    public void setId(long id) {
        this.id = id;
    }

    public long getId_api() {
        return id_api;
    }

    public void setId_api(long id_api) {
        this.id_api = id_api;
    }

    private long id_api;


    public Anime(){}

    public Anime(String nom, String alias, String producer, String image) {
        this.nom = nom;
        this.alias = alias;
        this.producer = producer;
        this.image = image;
    }

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Comments> getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(List<Comments> anime_id) {
        this.anime_id = anime_id;
    }
}
