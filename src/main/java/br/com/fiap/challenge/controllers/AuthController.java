package br.com.fiap.challenge.controllers;

import br.com.fiap.challenge.model.vo.UserRegisterVO;
import br.com.fiap.challenge.model.vo.security.AccountCredentialsVO;
import br.com.fiap.challenge.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticação", description = "API Restfull do Challenge, responsavel pela autenticação e operações do usuário.")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;


    @CrossOrigin(origins = "http://localhost:19006")
    @Operation(summary = "Faz o login.", description = "Autentica um usuário.",
            tags = {"Autenticação"})
    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody AccountCredentialsVO data) {
        if (data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid client request!");
        } else {
            var token = authServices.signIn(data);
            if (token == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid client request!");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(token);
            }
        }
    }

    @Operation(summary = "Faz o cadastro.", description = "Cadastra um usuário.",
            tags = {"Autenticação"})
    @PostMapping(value = "/signup")
    public ResponseEntity signUp(@RequestBody UserRegisterVO data) {
        if (data == null || data.email() == null || data.email().isBlank() || data.password() == null || data.password().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid client request!");
        } else {
            return authServices.signUp(data);

        }
    }

    @Operation(summary = "Faz a desativação de um usuário.", description = "Desativa um usuário.",
            tags = {"Autenticação"})
    @DeleteMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody AccountCredentialsVO data) {
        if (data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid client request!");
        } else {
            authServices.deleteUser(data);
            return ResponseEntity.status(HttpStatus.OK).body("User removed!");
        }
    }
}
