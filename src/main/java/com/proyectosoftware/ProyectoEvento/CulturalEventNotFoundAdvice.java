package com.proyectosoftware.ProyectoEvento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//This is the advice witch it triggers when the system doesn't found the cultural event requested.
@ControllerAdvice
class CulturalEventNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CulturalEventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(CulturalEventNotFoundException ex) {
        return ex.getMessage();
    }
}