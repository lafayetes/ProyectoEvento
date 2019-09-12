package com.proyectosoftware.ProyectoEvento;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
//this class was made for transform the CulturalEvent Object to a Resource<CulturalEvent> object
@Component
class CulturalEventResourceAssembler implements ResourceAssembler<CulturalEvent, Resource<CulturalEvent>> {

    @Override
    public Resource<CulturalEvent> toResource(CulturalEvent culturalEvent) {

        return new Resource<>(culturalEvent,
                linkTo(methodOn(CulturalEventController.class).one(culturalEvent.getId())).withSelfRel(),
                linkTo(methodOn(CulturalEventController.class).all()).withRel("culturalevents"));
    }
}
