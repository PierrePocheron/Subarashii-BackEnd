package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String Item1;

    @Column
    private String Item2;

    @Column
    private String Item3;

    @OneToMany @NotNull
    @JoinColumn( name = "animeId")
    private List<Rating> animeId;


    //region  === getter-setter ===

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem1() {
        return Item1;
    }

    public void setItem1(String item1) {
        Item1 = item1;
    }

    public String getItem2() {
        return Item2;
    }

    public void setItem2(String item2) {
        Item2 = item2;
    }

    public String getItem3() {
        return Item3;
    }

    public void setItem3(String item3) {
        Item3 = item3;
    }

    public List<Rating> getAnimeId() {
        return animeId;
    }

    public void setAnimeId(List<Rating> animeId) {
        this.animeId = animeId;
    }

    //endregion
}
