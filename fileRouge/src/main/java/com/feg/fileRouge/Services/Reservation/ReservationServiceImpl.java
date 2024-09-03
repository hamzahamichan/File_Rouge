package com.feg.fileRouge.Services.Reservation;

import com.feg.fileRouge.Entity.Dto.ReservationDto;
import com.feg.fileRouge.Entity.Model.Reservation;
import com.feg.fileRouge.Repository.ReservationRepository;
import com.feg.fileRouge.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {


   private final ReservationMapper reservationMapper;
    private  final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, ReservationMapper reservationMapper1) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper1;
    }

    @Override
    public ResponseEntity<Reservation> save(ReservationDto reservationDto) {
        Reservation reservation = this.reservationMapper.toEntity(reservationDto);
        Reservation reservation1 = this.reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        Optional<Reservation> optional = this.reservationRepository.findById(id);
        return this.reservationMapper.toDto(optional.get());
    }

    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Long id) {
      this.reservationRepository.deleteById(id);
    }
}
