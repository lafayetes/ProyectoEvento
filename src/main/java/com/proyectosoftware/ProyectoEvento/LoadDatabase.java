package com.proyectosoftware.ProyectoEvento;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//On this class we can add the events to the DataBase
@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CulturalEventRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new CulturalEvent("Feria Japonesa", "Obrajes","200","Feria Japonesa para exponer el arte y diferentes costumbres de Japòn en Bolivia","12/11/2019")));
            log.info("Preloading " + repository.save(new CulturalEvent("Alasitas", "Campo Ferial","2000","Feria de las alasitas para compartir con la familia","23/10/2019")));
            log.info("Preloading " + repository.save(new CulturalEvent("Feria del libro", "Campo Ferial","2000","Feria XXI Del libro ","17/10/2019")));


        };
    }
}