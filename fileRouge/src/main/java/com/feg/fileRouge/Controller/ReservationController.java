package com.feg.fileRouge.Controller;

import com.feg.fileRouge.Entity.Dto.ReservationDto;
import com.feg.fileRouge.Entity.Model.Reservation;
import com.feg.fileRouge.Services.Reservation.ReservationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

///api/reservation/faire
//api/reservation/afficher
// /api/reservation/supprimer
//api/reservation/modifier
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationServiceImpl service;

    public ReservationController(ReservationServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/faire")
    public ResponseEntity<Reservation> effectue (@RequestBody ReservationDto reservationDto){
        return  this.service.save(reservationDto);
    }

    @GetMapping("/afficher")
    public ReservationDto afficherReservationId(@RequestParam Long id){
        return this.service.getReservationById(id);
    }

    @PutMapping("/modifier")
    public Reservation update(@RequestParam Long id , @RequestBody ReservationDto reservationDto){
        return this.service.updateResevation(id,reservationDto);
    }
    @DeleteMapping("/supprimer")
    public void supprimerReservation(@RequestParam Long id){
        this.service.deleteReservation(id);
    }
}
