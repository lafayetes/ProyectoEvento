package com.proyectosoftware.ProyectoEvento;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class CulturalEvent {

    private @Id @GeneratedValue Long id;
    private String name;
    private String place;
    private String capacity;
    private String description;
    private String date;

    CulturalEvent() {}

    CulturalEvent(String name, String place,String capacity, String description,String date) {
        this.name = name;
        this.place = place;
        this.capacity = capacity;
        this.description = description;
        this.date = date;
    }
    public String getEvent(){
        return this.name + "|" +this.date;
    }
    public void setEvent(String event){
        String[] parts = event.split("|");
        this.name = parts[0];
        this.date = parts[1];
    }
}
