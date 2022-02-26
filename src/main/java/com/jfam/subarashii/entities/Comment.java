package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String Item1;

    @Column
    private String Item2;

    @Column
    private String Item3;


    @Column @NotNull
    private String Statut;

    @ManyToOne @NotNull
    @JoinColumn( name = "anime_id")
    private Anime anime;


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

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String statut) {
        Statut = statut;
    }





    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", Item1='" + Item1 + '\'' +
                ", Item2='" + Item2 + '\'' +
                ", Item3='" + Item3 + '\'' +
                ", Statut='" + Statut + '\'' +
                '}';
    }
}
