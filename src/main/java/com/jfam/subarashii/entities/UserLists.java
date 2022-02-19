package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "userlists")
public class UserLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NotBlank
    private String nom;

    @Column
    private boolean isDeletabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isDeletable() {
        return isDeletabled;
    }

    public void setDeletable(boolean deletable) {
        isDeletabled = deletable;
    }
}
