package com.feg.fileRouge.Repository;

import com.feg.fileRouge.Entity.Model.Reservation;
import com.feg.fileRouge.Entity.Model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    // Récupère toutes les réservations associées à une salle
    List<Reservation> findBySalle(Salle salle);

    @Query("SELECT COUNT(s) FROM Salle s WHERE s.client.idClient = :idClient")
    Long countByClientId(@Param("idClient") Long idClient);


    List<Reservation> findBySalleIdSalle(Long idSalle);

}
