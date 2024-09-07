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
    private String eventType;
    private Long nbrParticipants;
    private String details;
    private Date dateDebut;
    private Long idSalle;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "salle_id", referencedColumnName = "idSalle")
    private Salle salle;
}
