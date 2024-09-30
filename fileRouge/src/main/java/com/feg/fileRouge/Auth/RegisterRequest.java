package com.feg.fileRouge.Auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Builder
@NoArgsConstructor @Data
public class RegisterRequest {
    @NotEmpty(message = "Le nom ne peut pas être vide.")
    private String nom;

    @NotEmpty(message = "Le prénom ne peut pas être vide.")
    private String prenom;

    @NotEmpty(message = "L'email ne peut pas être vide.")
    @Email(message = "Format d'email invalide.")
    private String email;

    @NotEmpty(message = "Le mot de passe ne peut pas être vide.")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères.")
    private String motDePasse;
}
