package com.feg.fileRouge.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class AutheticateRequest {

    @NotEmpty(message = "L'email ne peut pas être vide.")
    @Email(message = "Format d'email invalide.")
    private String email;

    @NotEmpty(message = "Le mot de passe ne peut pas être vide.")
    private String motDePasse;
}
