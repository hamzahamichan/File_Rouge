package com.feg.fileRouge.Entity.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;
    private String nom;
    private String description;

    @ManyToOne
    @JoinColumn(name = "salle_id", referencedColumnName = "idSalle")
    private Salle salle;
}
