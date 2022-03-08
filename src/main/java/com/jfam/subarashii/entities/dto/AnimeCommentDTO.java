package com.jfam.subarashii.entities.dto;

import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.User;


public class AnimeCommentDTO {


private String contenu;
private Anime idApiAnime;

public AnimeCommentDTO(){

}

public AnimeComment toEntity(User user){

    AnimeComment animeComment = new AnimeComment();
    animeComment.setContenu(this.contenu);
    animeComment.setAnime(this.idApiAnime);

    return animeComment;

}

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Anime getIdApiAnime() {
        return idApiAnime;
    }

    public void setIdApiAnime(Anime idApiAnime) {
        this.idApiAnime = idApiAnime;
    }
}
