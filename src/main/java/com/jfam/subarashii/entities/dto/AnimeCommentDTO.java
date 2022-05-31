package com.jfam.subarashii.entities.dto;

import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.utils.Helpers;


public class AnimeCommentDTO {

private Long id;

private String contenu;

private Long idApiAnime;

private String nomAnime;

private String date;

private String nomUser;

private boolean isMineComment;

private String idUser;

public AnimeCommentDTO(){

}

public AnimeCommentDTO(AnimeComment animeComment, User userCurrent){
    this.id = animeComment.getId();
    this.contenu = animeComment.getContenu();
    this.idApiAnime = animeComment.getAnime().getIdApi();
    this.nomAnime = animeComment.getAnime().getNomTraduit();
    this.date = animeComment.getDate();
    this.nomUser = animeComment.getUser().getUsername();
    this.isMineComment = userCurrent.getIdUser() == animeComment.getUser().getIdUser();
    this.idUser = animeComment.getId().toString();
}

public AnimeComment toEntity(User user, Anime anime){
    AnimeComment animeComment = new AnimeComment();
    animeComment.setContenu(this.contenu);
    animeComment.setUser(user);
    animeComment.setAnime(anime);
    animeComment.setDate(Helpers.getDateNow());
    animeComment.setDate(Helpers.getDateNow());
    return animeComment;
}

//#region === GETTER / SETTER


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public boolean isMineComment() {
        return isMineComment;
    }

    public void setMineComment(boolean mineComment) {
        isMineComment = mineComment;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Long getIdApiAnime() {
        return idApiAnime;
    }

    public void setIdApiAnime(Long idApiAnime) {
        this.idApiAnime = idApiAnime;
    }

    public String getNomAnime() {
        return nomAnime;
    }

    public void setNomAnime(String nomAnime) {
        this.nomAnime = nomAnime;
    }

    //endregion
}
