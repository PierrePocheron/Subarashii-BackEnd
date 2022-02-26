package com.jfam.subarashii.entities;


import com.google.gson.JsonObject;

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

    private String title;

    private String title_english;

    private String title_japanese;

    private Float episodes;

    public Float getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Float episodes) {
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getTitle_english() {
        return title_english;
    }

    public void setTitle_english(String title_english) {
        this.title_english = title_english;
    }

    public String getTitle_japanese() {
        return title_japanese;
    }

    public void setTitle_japanese(String title_japanese) {
        this.title_japanese = title_japanese;
    }

    @OneToMany
    @JoinColumn( name = "anime_id")
    private List<Comment> anime_id;


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

    public Anime(JsonObject jsonObject) {
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

    public List<Comment> getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(List<Comment> anime_id) {
        this.anime_id = anime_id;
    }
}
