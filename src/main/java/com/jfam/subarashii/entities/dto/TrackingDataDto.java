package com.jfam.subarashii.entities.dto;

import java.time.LocalDateTime;


public abstract class TrackingDataDto {

    // Fields

    private LocalDateTime dateCreation;


    // Constructor

    public TrackingDataDto(){

    }

    public TrackingDataDto(LocalDateTime dateCreation) {
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
