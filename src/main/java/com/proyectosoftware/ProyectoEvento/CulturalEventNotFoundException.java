package com.proyectosoftware.ProyectoEvento;


class CulturalEventNotFoundException extends RuntimeException {

    CulturalEventNotFoundException (Long id) {
        super("Could not find the requested event " + id);
    }
}