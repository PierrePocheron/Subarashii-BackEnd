package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NotNull
    private long apiImageId;

    @Column @NotNull
    private String urlImage;

    //region  === getter-setter ===

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getApiImageId() {
        return apiImageId;
    }

    public void setApiImageId(long apiImageId) {
        this.apiImageId = apiImageId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    //endregion
}
