package com.feg.fileRouge.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReservationDto {
    private Long idReservation;
    private String eventType;
    private Long nbrParticipants;
    private String details;
    private LocalDateTime dateDeFin;
    private LocalDateTime dateDebut;
    private Long idSalle;
}
