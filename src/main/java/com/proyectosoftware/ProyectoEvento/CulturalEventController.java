package com.proyectosoftware.ProyectoEvento;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
class CulturalEventController{

    private final CulturalEventRepository repository;
    private final CulturalEventResourceAssembler assembler;
    CulturalEventController(CulturalEventRepository repository,
                            CulturalEventResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
// Here is the get method using REST to get the all the data
    @GetMapping("/culturalevents")
    Resources<Resource<CulturalEvent>> all() {

        List<Resource<CulturalEvent>> culturalEvent = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(culturalEvent,
                linkTo(methodOn(CulturalEventController.class).all()).withSelfRel());
    }
//Otherwise here is the post method  to add new events
    @PostMapping("/culturalevents")
    ResponseEntity<?> newEmployee(@RequestBody CulturalEvent newCulturalEvent) throws URISyntaxException {

        Resource<CulturalEvent> resource = assembler.toResource(repository.save(newCulturalEvent));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    // Single item
// This GET method is for get a singular event using the id of the event
    @GetMapping("/culturalevents/{id}")
    Resource<CulturalEvent> one(@PathVariable Long id) {

        CulturalEvent culturalEvent = repository.findById(id)
                .orElseThrow(() -> new CulturalEventNotFoundException(id));

        return assembler.toResource(culturalEvent);
    }
// The PUT method is implemented here to edit any event made
    @PutMapping("/culturalevents/{id}")
    ResponseEntity<?> replaceCulturalEvent(@RequestBody CulturalEvent newCulturalEvent, @PathVariable Long id) throws URISyntaxException{


        CulturalEvent updatedCulturalEvent = repository.findById(id)
                .map(CulturalEvent -> {
                    CulturalEvent.setName(newCulturalEvent.getName());
                    CulturalEvent.setPlace(newCulturalEvent.getPlace());
                    CulturalEvent.setCapacity(newCulturalEvent.getCapacity());
                    CulturalEvent.setDescription(newCulturalEvent.getDescription());
                    CulturalEvent.setDate(newCulturalEvent.getDate());
                    return repository.save(CulturalEvent);
                })
                .orElseGet(() -> {
                    newCulturalEvent.setId(id);
                    return repository.save(newCulturalEvent);
                });
        Resource<CulturalEvent> resource = assembler.toResource(updatedCulturalEvent);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }
// Finally here is the Delete method for the events
    @DeleteMapping("/culturalevents/{id}")
    ResponseEntity<?> deleteCulturalEvent(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}