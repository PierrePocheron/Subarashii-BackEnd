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
    @JoinColumn( name = "user_id")
    private List<See> user_id;

    @OneToMany  @NotNull
    @JoinColumn( name = "episode_id")
    private List<See> episode_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<See> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<See> user_id) {
        this.user_id = user_id;
    }

    public List<See> getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(List<See> episode_id) {
        this.episode_id = episode_id;
    }
}
