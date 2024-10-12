package com.feg.fileRouge.Entity.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvis;
    private String commentaire;
    private String email;
    private String nomComplet;
    private int note;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "salle_id", referencedColumnName = "idSalle")
    private Salle salle;
}
