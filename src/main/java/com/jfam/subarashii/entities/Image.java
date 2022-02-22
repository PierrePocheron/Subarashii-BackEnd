package com.jfam.subarashii.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NotBlank @NotNull
    private long api_image_id;

    @Column @NotBlank @NotNull
    private String url_image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUrl_image_id() {
        return api_image_id;
    }

    public void setUrl_image_id(long url_image_id) {
        this.api_image_id = url_image_id;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", api_image_id=" + api_image_id +
                ", url_image='" + url_image + '\'' +
                '}';
    }
}
