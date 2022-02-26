package com.jfam.subarashii.entities;


import com.google.gson.JsonObject;
import com.jfam.subarashii.utils.Constantes;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "animes")
public class Anime {

    @Id
    @GeneratedValue
    private Long id;

    private Long idApi;

    private String nom;

    private Long nbSaison;

    private Long nbEpisodes;

    private String image;

    @Column(columnDefinition = "text")
    private String description;

    private Float note;

    @OneToMany
    @JoinColumn( name = "anime")
    private List<Comment> comments;

    @OneToMany(mappedBy = "anime")
    private List<Episode> episodes;

    public Anime(){}


    public Anime(JsonObject jsonObject) {
        this.idApi = jsonObject.get("id").getAsLong();
        this.nom = jsonObject.get("name").getAsString();
        this.nbSaison = jsonObject.get("number_of_seasons").getAsLong();
        this.nbEpisodes = jsonObject.get("number_of_episodes").getAsLong();
        this.description = jsonObject.get("overview").getAsString();
        this.note = jsonObject.get("vote_average").getAsFloat();
        this.image = Constantes.ApiMovie.URL_IMAGE +  jsonObject.get("poster_path").getAsString();
    }

    //region  === getter-setter ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdApi() {
        return idApi;
    }

    public void setIdApi(Long idApi) {
        this.idApi = idApi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getNbSaison() {
        return nbSaison;
    }

    public void setNbSaison(Long nbSaison) {
        this.nbSaison = nbSaison;
    }

    public Long getNbEpisodes() {
        return nbEpisodes;
    }

    public void setNbEpisodes(Long nbEpisodes) {
        this.nbEpisodes = nbEpisodes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
//endregion
}
