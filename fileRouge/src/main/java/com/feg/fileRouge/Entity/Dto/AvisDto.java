package com.feg.fileRouge.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvisDto {
    private Long idAvis;
    private String commentaire;
    private String email;
    private String nomComplet;
    private int note;
}
