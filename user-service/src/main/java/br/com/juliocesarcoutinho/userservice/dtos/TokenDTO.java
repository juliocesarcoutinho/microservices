package br.com.juliocesarcoutinho.userservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(name = "Token", description = "Token Atributes for Authentication")
public record TokenDTO(String email, Boolean authenticated, Date created, Date expiration, String accessToken,
                       String refreshToken) {}
