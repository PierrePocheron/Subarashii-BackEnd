package com.jfam.subarashii.entities;

import javax.persistence.*;

@Entity
@Table(name = "userlistsAnimes")
public class UserListAnime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
