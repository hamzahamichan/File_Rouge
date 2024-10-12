package com.feg.fileRouge.Services.salle;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Client;
import com.feg.fileRouge.Entity.Model.Reservation;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Enum.StatutOfSalle;
import com.feg.fileRouge.Repository.ClientRepository;
import com.feg.fileRouge.Repository.ReservationRepository;
import com.feg.fileRouge.Repository.SalleRepository;
import com.feg.fileRouge.mapper.SalleMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleServiceImpl implements SalleService{

    private final ClientRepository clientRepository;
    private final SalleMapper salleMapper;
    private final SalleRepository salleRepository;
    private final ReservationRepository reservationRepository;

    public SalleServiceImpl(ClientRepository clientRepository, SalleMapper salleMapper, SalleRepository salleRepository, ReservationRepository reservationRepository) {
        this.clientRepository = clientRepository;
        this.salleMapper = salleMapper;
        this.salleRepository = salleRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public ResponseEntity<Salle> save(SallesDto dto) {
        System.out.println("DTO avant conversion: " + dto);
        Salle salle = salleMapper.toEntity(dto);
        salle.setStatut(StatutOfSalle.Desponible); // Convertit le DTO en entité
        Client client=clientRepository.findById(dto.getIdClient()).orElseThrow(()-> new RuntimeException("not found"+ dto.getIdClient()));
        salle.setClient(client);
        System.out.println("Entité Salle créée: " + salle); // Log de l'entité avant sauvegarde
        Salle savedSalle = salleRepository.save(salle); // Sauvegarde l'entité en base de données
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSalle); // Retourne la réponse avec le statut 201
    }


    @Override
    public SallesDto updateSalle(SallesDto dto) {
        Salle salleEntity = this.salleMapper.toEntity(dto);
        Salle updatedSalle = this.salleRepository.save(salleEntity);
        return this.salleMapper.toDto(updatedSalle);
    }


        @Override
        public ResponseEntity<Void> deleteSalle(Long id) {
            // Récupérer la salle par son ID
            Optional<Salle> salleOptional = salleRepository.findById(id);

            if (salleOptional.isPresent()) {
                Salle salle = salleOptional.get();

                // Récupérer toutes les réservations associées à cette salle
                List<Reservation> reservations = reservationRepository.findBySalle(salle);

                // Dissocier la salle des réservations avant de supprimer la salle
                for (Reservation reservation : reservations) {
                    reservation.setSalle(null);  // Supprimer la référence à la salle
                    reservationRepository.save(reservation);  // Mettre à jour la réservation
                }

                // Après avoir dissocié les réservations, supprimer la salle
                salleRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Salle with ID " + id + " not found.");
            }
            return null;
        }


    @Override
    public SallesDto getSalleById(Long id) {
        Optional<Salle> salle = salleRepository.findById(id);
        if (salle.isPresent()) {
            SallesDto salleDto = salleMapper.toDto(salle.get());
            System.out.println("DTO généré: " + salleDto);  // Debug
            return salleDto;
        } else {
            return null;
        }
    }

    @Override
    public List<Salle> getAllSalles(Long idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec ID " + idClient));

        List<Salle> list = salleRepository.findByClient(client); // Utilisation de l'objet Client
        if (list.isEmpty()) {
            throw new EntityNotFoundException("Aucune salle trouvée pour le client avec ID " + idClient + ".");
        }
        return list;
    }


    @Override
    public List<Salle> getAllSalle() {
        List<Salle> list = salleRepository.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("Liste n'existe pas");
        }else
            return list;
    }

    @Override
    public Long compterSalles(Long idClient) {
        try {
            Long nombre = salleRepository.count(); // Utilisez Long ici pour correspondre au type de retour
            return nombre;
        } catch (Exception e) {

            throw new RuntimeException("Erreur lors du comptage des salles.", e);
        }
    }

    @Override
    public List<Salle> searchSalles(String nom, String emplacement) {
        try {
            return  this.salleRepository.findByCriteria(nom,emplacement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



