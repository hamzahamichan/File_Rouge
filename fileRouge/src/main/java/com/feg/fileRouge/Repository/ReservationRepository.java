package com.feg.fileRouge.Repository;

import com.feg.fileRouge.Entity.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
