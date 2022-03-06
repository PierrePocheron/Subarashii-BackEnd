package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "see")
public class See {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany  @NotNull
    @JoinColumn( name = "userId")
    private List<See> userId;

    @OneToMany  @NotNull
    @JoinColumn( name = "episodeId")
    private List<See> episodeId;


    //region  === getter-setter ===

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<See> getUserId() {
        return userId;
    }

    public void setUserId(List<See> userId) {
        this.userId = userId;
    }

    public List<See> getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(List<See> episodeId) {
        this.episodeId = episodeId;
    }

    //endregion
}
