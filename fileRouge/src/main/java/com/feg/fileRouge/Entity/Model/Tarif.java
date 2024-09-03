package com.feg.fileRouge.Entity.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tarif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarif;
    private Double montant;
    private String description;

    @OneToOne(mappedBy = "tarif")
    private Salle salle;

    @OneToOne(mappedBy = "tarif")
    private Service service;
}
