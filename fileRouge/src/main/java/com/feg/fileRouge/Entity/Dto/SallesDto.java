package com.feg.fileRouge.Entity.Dto;

import com.feg.fileRouge.Enum.StatutOfSalle;
import lombok.*;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder

public class SallesDto {
    private Long idSalle;
    private String nom;
    private String description;
    private int capacite;
    private String emplacement;
    private Double prix;
    private StatutOfSalle statut;

}
