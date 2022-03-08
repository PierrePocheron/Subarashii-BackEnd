package com.jfam.subarashii.entities.dto;

import com.jfam.subarashii.entities.Anime;
import com.jfam.subarashii.entities.AnimeComment;
import com.jfam.subarashii.entities.User;


public class AnimeCommentDTO {


private String contenu;
private Long idApiAnime;

public AnimeCommentDTO(){

}

public AnimeComment toEntity(User user, Anime anime){

    AnimeComment animeComment = new AnimeComment();
    animeComment.setContenu(this.contenu);
    animeComment.setUser(user);
    animeComment.setAnime(anime);


    return animeComment;

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
}
