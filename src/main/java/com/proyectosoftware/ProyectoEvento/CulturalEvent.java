package com.proyectosoftware.ProyectoEvento;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class CultEvent {

    private @Id @GeneratedValue Long id;
    private String name;
    private String place;
    private int capacity;
    private String description;

    CultEvent() {}

    CultEvent(String name, String place,int capacity, String description) {
        this.name = name;
        this.place = place;
        this.capacity = capacity;
        this.description = description;
    }
}
