package com.jfam.subarashii.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jfam.subarashii.utils.Constantes;

import javax.persistence.*;
import java.text.ParseException;
import java.util.List;

@Entity
@Table(name = "animes")
@JsonIgnoreProperties(value = { "episodes"},allowSetters = true)
public class Anime {

    @Id
    @GeneratedValue
    private Long id;

    private Long idApi;

    private String nomTraduit;

    private String originalName;

    private Long nbSaison;

    private Long nbEpisodes;

    private String image;

    @Column(columnDefinition = "text")
    private String description;

    private Float note;


    @OneToMany(mappedBy = "anime")
    private List<Episode> episodes;

    @ManyToMany(mappedBy = "animes")
    @JsonBackReference
    private List<UserList> userLists;

    @OneToMany(mappedBy = "anime")
    @JsonBackReference(value = "anime-comments")
    private List<AnimeComment> animeComments;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "anime_genres",
            joinColumns = @JoinColumn(name = "animeId"),
            inverseJoinColumns = {@JoinColumn(name = "genreId")}
    )
    private List<Genre> genres;

    private String backgroundPath;

    private Boolean isAdult;

    private String dateCommencement;

    private String dateFin;

    private Boolean enCours;

    public Anime(){}

    public Anime(JsonObject jsonObject) throws ParseException {
        this.idApi = jsonObject.get("id").getAsLong();
        this.nomTraduit = jsonObject.get("name").getAsString();
        this.nbSaison = jsonObject.get("number_of_seasons").getAsLong();
        this.nbEpisodes = jsonObject.get("number_of_episodes").getAsLong();
        this.description = jsonObject.get("overview").getAsString();
        this.note = jsonObject.get("vote_average").getAsFloat();
        this.isAdult = jsonObject.get("adult").getAsBoolean();
        this.dateCommencement = jsonObject.get("first_air_date").getAsString();
        this.dateFin = jsonObject.get("last_air_date").getAsString();
        this.enCours = jsonObject.get("in_production").getAsBoolean();
        this.originalName = jsonObject.get("original_name").getAsString();

        JsonElement jsonPosterPath = jsonObject.get("poster_path");
        this.image =  jsonPosterPath == null ? Constantes.URL_IMAGE_NOT_FOUND : Constantes.ApiMovie.URL_IMAGE +  jsonObject.get("poster_path").getAsString();

        JsonElement jsonBackgroundPath= jsonObject.get("backdrop_path");
        this.backgroundPath =  jsonBackgroundPath == null ? Constantes.URL_IMAGE_NOT_FOUND : Constantes.ApiMovie.URL_IMAGE_ORIGINE +  jsonObject.get("backdrop_path").getAsString();
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

    public String getNomTraduit() {
        return nomTraduit;
    }

    public void setNomTraduit(String nomTraduit) {
        this.nomTraduit = nomTraduit;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public List<AnimeComment> getAnimeComments() {
        return animeComments;
    }

    public void setAnimeComments(List<AnimeComment> animeComments) {
        this.animeComments = animeComments;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }

    public String getDateCommencement() {
        return dateCommencement;
    }

    public void setDateCommencement(String dateCommencement) {
        this.dateCommencement = dateCommencement;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getEnCours() {
        return enCours;
    }

    public void setEnCours(Boolean enCours) {
        this.enCours = enCours;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", idApi=" + idApi +
                ", nomTraduit='" + nomTraduit + '\'' +
                ", originalName='" + originalName + '\'' +
                ", nbSaison=" + nbSaison +
                ", nbEpisodes=" + nbEpisodes +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", note=" + note +
                ", episodes=" + episodes +
                ", userLists=" + userLists +
                ", genres=" + genres +
                ", backgroundPath='" + backgroundPath + '\'' +
                ", isAdult=" + isAdult +
                ", dateCommencement='" + dateCommencement + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", enCours=" + enCours +
                '}';
    }

//endregion
}
