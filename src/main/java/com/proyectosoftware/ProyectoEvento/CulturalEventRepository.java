package com.proyectosoftware.ProyectoEvento;

import org.springframework.data.jpa.repository.JpaRepository;
// Here is where we have the interface for the event repository
interface CulturalEventRepository extends JpaRepository<CulturalEvent, Long> {

}