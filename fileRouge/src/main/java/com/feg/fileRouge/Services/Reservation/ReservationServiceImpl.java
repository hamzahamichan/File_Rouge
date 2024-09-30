package com.feg.fileRouge.Services.Reservation;

import com.feg.fileRouge.Entity.Dto.ReservationDto;
import com.feg.fileRouge.Entity.Model.Reservation;
import com.feg.fileRouge.Repository.ReservationRepository;
import com.feg.fileRouge.mapper.ReservationMapper;
import jakarta.transaction.SystemException;
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
        if (reservationDto.getEventType() == null || reservationDto.getEventType().isEmpty()) {
            System.out.println("Le type d'événement est requis.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        if (reservationDto.getDateDebut() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
        Reservation reservation = this.reservationMapper.toEntity(reservationDto);
        Reservation reservation1 = this.reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation1);
    }


    @Override
    public ReservationDto getReservationById(Long id) {
        Optional<Reservation> optional = this.reservationRepository.findById(id);
        if (optional.isPresent()){
            return this.reservationMapper.toDto(optional.get());
        }
        else return null;
    }


    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> list =this.reservationRepository.findAll();
        if(list.isEmpty()){
            return null;
        }else
            return list;
    }

    @Override
    public void deleteReservation(Long id) {
      this.reservationRepository.deleteById(id);
    }

    @Override
    public Reservation updateResevation(Long id , ReservationDto dto) {

        Optional<Reservation> reservation = this.reservationRepository.findById(id);
        if(reservation.isPresent()){
            Reservation reservation1 = this.reservationMapper.toEntity(dto);
            Reservation reservation2= this.reservationRepository.save(reservation1);
            return reservation1;
        }
       else return null;
    }

    @Override
    public ReservationDto searchReservationByName(String eventType) {

        return null;
    }

}
