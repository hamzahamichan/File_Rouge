package com.feg.fileRouge.Services.Reservation;

import com.feg.fileRouge.Entity.Dto.ReservationDto;
import com.feg.fileRouge.Entity.Model.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    ResponseEntity<Reservation> save(ReservationDto reservationDto);

    ReservationDto getReservationById(Long id);

    List<Reservation> getAllReservations();

    void deleteReservation(Long id);
}
