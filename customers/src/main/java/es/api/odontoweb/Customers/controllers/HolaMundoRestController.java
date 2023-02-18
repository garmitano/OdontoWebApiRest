package es.api.odontoweb.Customers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRestController {
    @GetMapping("/hola/{nombreUsuario}")
    public String saludo(@PathVariable String nombreUsuario){
        System.out.println("Ejecutando Hola Mundo en 8080");
        return "Hola mundo " + nombreUsuario;
    }
}
