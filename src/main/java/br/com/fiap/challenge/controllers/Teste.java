package br.com.fiap.challenge.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class Teste {

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public String teste() {
        return "Teste";
    }
}
