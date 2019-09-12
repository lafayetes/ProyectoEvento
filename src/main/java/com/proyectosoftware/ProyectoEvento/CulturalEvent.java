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
// Here we are making the class called CulturalEvent which will have the attributes name, place, capacity, description and date
    CulturalEvent(String name, String place,String capacity, String description,String date) {
        this.name = name;
        this.place = place;
        this.capacity = capacity;
        this.description = description;
        this.date = date;
    }
// I made a getter for the name and the date of the event, so we can get the name and the date of the event
    public String getEvent(){
        return this.name + " " +this.date;
    }
// And here we have the setter dividing the name and the date.
    public void setEvent(String event){
        String[] parts = event.split(" ");
        this.name = parts[0];
        this.date = parts[1];
    }
}
