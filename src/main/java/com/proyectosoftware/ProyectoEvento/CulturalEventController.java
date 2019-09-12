package com.proyectosoftware.ProyectoEvento;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CulturalEventController{

    private final CulturalEventRepository repository;

    CulturalEventController(CulturalEventRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/culturalevents")
    List<CulturalEvent> all() {
        return repository.findAll();
    }

    @PostMapping("/culturalevents")
    CulturalEvent newCulturalEvent(@RequestBody CulturalEvent newCulturalEvent) {
        return repository.save(newCulturalEvent);
    }

    // Single item

    @GetMapping("/culturalevents/{id}")
    CulturalEvent one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new CulturalEventNotFoundException(id));
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