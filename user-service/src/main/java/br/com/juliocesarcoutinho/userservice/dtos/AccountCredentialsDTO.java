package br.com.juliocesarcoutinho.userservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Account Credentials", description = "Account credentials (email and password)")
public record AccountCredentialsDTO(String email, String password) {}