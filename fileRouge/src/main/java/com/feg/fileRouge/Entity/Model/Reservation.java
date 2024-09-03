package com.feg.fileRouge.Entity.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private Date dateReservation;
    private Date dateDebut;
    private Date dateFin;
    private Double tarifTotal;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "salle_id", referencedColumnName = "idSalle")
    private Salle salle;
}
