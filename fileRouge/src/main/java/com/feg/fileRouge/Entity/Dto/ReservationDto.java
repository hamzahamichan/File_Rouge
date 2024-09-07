package com.feg.fileRouge.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReservationDto {
    private Long idReservation;
    private String eventType;
    private Long nbrParticipants;
    private String details;
    private Date dateDebut;
    private Long idSalle;
}
