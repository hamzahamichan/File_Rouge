package com.feg.fileRouge.Services.Reservation;

import com.feg.fileRouge.Entity.Dto.ReservationDto;
import com.feg.fileRouge.Entity.Model.Reservation;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Enum.StatutOfSalle;
import com.feg.fileRouge.Repository.ReservationRepository;
import com.feg.fileRouge.Repository.SalleRepository;
import com.feg.fileRouge.mapper.ReservationMapper;
import jakarta.transaction.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationServiceImpl implements ReservationService {


   private final ReservationMapper reservationMapper;
    private  final ReservationRepository reservationRepository;
    private final SalleRepository salleRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, ReservationMapper reservationMapper1, SalleRepository salleRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper1;
        this.salleRepository = salleRepository;
    }

    @Override
    public ResponseEntity<Reservation> save(ReservationDto reservationDto) {
        // Vérification des champs obligatoires
        if (reservationDto.getEventType() == null || reservationDto.getEventType().isEmpty()) {
            System.out.println("Le type d'événement est requis.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (reservationDto.getDateDebut() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
        if (reservationDto.getDateDeFin() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
        if (reservationDto.getDateDebut().isAfter(reservationDto.getDateDeFin())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // La date de début ne peut pas être après la date de fin
        }

        // Vérification de la disponibilité de la salle
        Salle salle = salleRepository.findById(reservationDto.getIdSalle())
                .orElseThrow(() -> new RuntimeException("Salle non trouvée avec l'ID : " + reservationDto.getIdSalle()));

        if (salle.getStatut() != StatutOfSalle.Desponible) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Salle non disponible
        }

        // Conversion du DTO en entité
        Reservation reservation = this.reservationMapper.toEntity(reservationDto);

        // Enregistrement de la réservation
        Reservation savedReservation = this.reservationRepository.save(reservation);

        // Mise à jour du statut de la salle à "Réservée"
        salle.setStatut(StatutOfSalle.Reservee);
        salleRepository.save(salle); // Enregistrer les modifications du statut de la salle

        // Planification pour remettre la salle à "Disponible" après la date de fin
        scheduleSalleAvailabilityUpdate(salle, reservation.getDateDeFin());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
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

    @Override
    public List<Reservation> findBySalle(Long idSalle) {
        Salle salle = salleRepository.findById(idSalle)
                .orElseThrow(() -> new RuntimeException());
        return reservationRepository.findBySalleIdSalle(idSalle);
    }



    private void scheduleSalleAvailabilityUpdate(Salle salle, LocalDateTime dateDeFin) {
        long delay = ChronoUnit.MILLIS.between(LocalDateTime.now(), dateDeFin);
        if (delay > 0) {
            // Utilisation d'un exécuteur planifié pour mettre à jour le statut après la fin de la réservation
            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                salle.setStatut(StatutOfSalle.Desponible);
                salleRepository.save(salle);
                System.out.println("Statut de la salle " + salle.getIdSalle() + " mis à jour à 'Disponible'.");
            }, delay, TimeUnit.MILLISECONDS);
        }
    }

}
