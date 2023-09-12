package br.com.fiap.challenge.model.vo;

public record UserRegisterVO (
        String name,
        String email,
        String password
) {
}
