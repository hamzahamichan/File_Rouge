package com.feg.fileRouge.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ServiceDto {
    private Long idService;
    private String nom;
    private String description;
    private Long idSalle;
}
