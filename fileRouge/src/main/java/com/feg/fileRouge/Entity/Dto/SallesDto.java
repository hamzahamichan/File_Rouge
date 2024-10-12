package com.feg.fileRouge.Entity.Dto;

import com.feg.fileRouge.Enum.StatutOfSalle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder

public class SallesDto {
    private Long idSalle;
    private String nom;
    private String description;
    private int capacite;
    private String emplacement;
    private Double prix;
    private String eventType;
    private List<String> services;
    // Ajout de l'identifiant du client
    private Long idClient;

    @Enumerated(EnumType.STRING)
    private StatutOfSalle statut;

}
