package com.jfam.subarashii.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;



@MappedSuperclass
public abstract class TrackingData {

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;


    // Constructor

    public TrackingData(){

    }

    public TrackingData(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }


    // Getters & Setters

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
