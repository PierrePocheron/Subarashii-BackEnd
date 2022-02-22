package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NotBlank
    @NotNull
    private long id_episode_api;

    @Column @NotBlank @NotNull
    private String name;


    @OneToMany @NotBlank @NotNull
    @JoinColumn( name = "episode_id")
    private List<Comment> episode_id;

    @OneToMany @NotBlank @NotNull
    @JoinColumn( name = "anime_id")
    private List<Episode> anime_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_episode_api() {
        return id_episode_api;
    }

    public void setId_episode_api(long id_episode_api) {
        this.id_episode_api = id_episode_api;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public List<Comment> getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(List<Comment> episode_id) {
        this.episode_id = episode_id;
    }

    public List<Episode> getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(List<Episode> anime_id) {
        this.anime_id = anime_id;
    }
}
