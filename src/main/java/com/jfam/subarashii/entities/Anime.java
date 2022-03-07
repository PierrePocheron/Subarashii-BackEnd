package com.jfam.subarashii.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.JsonElement;
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



    @OneToMany(mappedBy = "anime")
    private List<Episode> episodes;


    @ManyToMany(mappedBy = "animes")
    private List<UserList> userLists;

    @OneToMany(mappedBy = "anime")
    @JsonIgnoreProperties
    private List<AnimeComment> animeComments;


    @OneToMany
    @JoinColumn(name = "idEpisode")
    private List<EpisodeComment> episodeComments;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "anime_genres",
            joinColumns = @JoinColumn(name = "animeId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private List<Genre> genres;

    public Anime(){}

    public Anime(JsonObject jsonObject) {
        this.idApi = jsonObject.get("id").getAsLong();
        this.nom = jsonObject.get("name").getAsString();
        this.nbSaison = jsonObject.get("number_of_seasons").getAsLong();
        this.nbEpisodes = jsonObject.get("number_of_episodes").getAsLong();
        this.description = jsonObject.get("overview").getAsString();
        this.note = jsonObject.get("vote_average").getAsFloat();

        JsonElement jsonElement = jsonObject.get("poster_path");
        this.image =  jsonElement == null ? Constantes.URL_IMAGE_NOT_FOUND : Constantes.ApiMovie.URL_IMAGE +  jsonObject.get("poster_path").getAsString();


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



    public List<UserList> getUserLists() {
        return userLists;
    }

    public void setUserLists(List<UserList> userLists) {
        this.userLists = userLists;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }




    public List<AnimeComment> getAnimeComments() {
        return animeComments;
    }

    public void setAnimeComments(List<AnimeComment> animeComments) {
        this.animeComments = animeComments;
    }

    public List<EpisodeComment> getEpisodeComments() {
        return episodeComments;
    }

    public void setEpisodeComments(List<EpisodeComment> episodeComments) {
        this.episodeComments = episodeComments;
    }

    //endregion
}
