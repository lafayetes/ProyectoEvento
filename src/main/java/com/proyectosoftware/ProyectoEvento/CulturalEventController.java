package com.proyectosoftware.ProyectoEvento;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

    @GetMapping("/culturalevents")
    Resources<Resource<CulturalEvent>> all() {

        List<Resource<CulturalEvent>> culturalEvent = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(culturalEvent,
                linkTo(methodOn(CulturalEventController.class).all()).withSelfRel());
    }

    @PostMapping("/culturalevents")
    CulturalEvent newCulturalEvent(@RequestBody CulturalEvent newCulturalEvent) {
        return repository.save(newCulturalEvent);
    }

    // Single item

    @GetMapping("/culturalevents/{id}")
    Resource<CulturalEvent> one(@PathVariable Long id) {

        CulturalEvent culturalEvent = repository.findById(id)
                .orElseThrow(() -> new CulturalEventNotFoundException(id));

        return assembler.toResource(culturalEvent);
    }
    @PutMapping("/culturalevents/{id}")
    CulturalEvent replaceCulturalEvent(@RequestBody CulturalEvent newCulturalEvent, @PathVariable Long id) {

        return repository.findById(id)
                .map(CulturalEvent -> {
                    CulturalEvent.setName(newCulturalEvent.getName());
                    CulturalEvent.setPlace(newCulturalEvent.getPlace());
                    CulturalEvent.setCapacity(newCulturalEvent.getCapacity());
                    CulturalEvent.setDescription(newCulturalEvent.getDescription());
                    return repository.save(CulturalEvent);
                })
                .orElseGet(() -> {
                    newCulturalEvent.setId(id);
                    return repository.save(newCulturalEvent);
                });
    }

    @DeleteMapping("/culturalevents/{id}")
    void deleteCulturalEvent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}