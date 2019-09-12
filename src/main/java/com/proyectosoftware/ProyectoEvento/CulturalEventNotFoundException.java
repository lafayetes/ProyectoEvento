package com.proyectosoftware.ProyectoEvento;

//This Exception is made for catch the error if we don't find the requested cultural event
class CulturalEventNotFoundException extends RuntimeException {

    CulturalEventNotFoundException (Long id) {
        super("Could not find the requested event " + id);
    }
}