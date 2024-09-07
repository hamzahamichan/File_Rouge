package com.feg.fileRouge.Entity.Dto;

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

}
