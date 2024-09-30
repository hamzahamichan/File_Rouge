package com.feg.fileRouge.Services.salle;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Enum.StatutOfSalle;
import com.feg.fileRouge.Repository.SalleRepository;
import com.feg.fileRouge.mapper.SalleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SalleServiceImplTest {

    @Mock
    private SalleMapper salleMapper;

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;

    private Salle salle;
    private SallesDto sallesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        salle = new Salle(); // Créez un objet Salle valide
        salle.setIdSalle(1L);
        salle.setNom("Salle A");
        salle.setStatut(StatutOfSalle.Desponible);

        sallesDto = new SallesDto(); // Créez un objet SallesDto valide
        sallesDto.setIdSalle(1L);
        sallesDto.setNom("Salle A");
    }

    @Test
    void save() {
        when(salleMapper.toEntity(any(SallesDto.class))).thenReturn(salle);
        when(salleRepository.save(any(Salle.class))).thenReturn(salle);

        ResponseEntity<Salle> response = salleService.save(sallesDto);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Salle A", response.getBody().getNom());
        assertEquals(StatutOfSalle.Desponible, response.getBody().getStatut());
        verify(salleRepository, times(1)).save(salle);
    }

    @Test
    void updateSalle() {
        when(salleMapper.toEntity(any(SallesDto.class))).thenReturn(salle);
        when(salleRepository.save(any(Salle.class))).thenReturn(salle);
        when(salleMapper.toDto(any(Salle.class))).thenReturn(sallesDto);

        SallesDto updatedSalleDto = salleService.updateSalle(sallesDto);

        assertNotNull(updatedSalleDto);
        assertEquals("Salle A", updatedSalleDto.getNom());
        verify(salleRepository, times(1)).save(salle);
    }

    @Test
    void deleteSalle() {
        doNothing().when(salleRepository).deleteById(salle.getIdSalle());

        salleService.deleteSalle(salle.getIdSalle());

        verify(salleRepository, times(1)).deleteById(salle.getIdSalle());
    }

    @Test
    void getSalleById() {
        when(salleRepository.findById(salle.getIdSalle())).thenReturn(Optional.of(salle));
        when(salleMapper.toDto(any(Salle.class))).thenReturn(sallesDto);

        SallesDto result = salleService.getSalleById(salle.getIdSalle());

        assertNotNull(result);
        assertEquals("Salle A", result.getNom());
    }

    @Test
    void getAllSalles() {
        List<Salle> salles = new ArrayList<>();
        salles.add(salle);
        when(salleRepository.findAll()).thenReturn(salles);

        List<Salle> result = salleService.getAllSalles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Salle A", result.get(0).getNom());
    }

    @Test
    void compterSalles() {
        when(salleRepository.count()).thenReturn(1L);

        Long count = salleService.compterSalles();

        assertEquals(1L, count);
    }

    @Test
    void searchSalles() {
        List<Salle> salles = new ArrayList<>();
        salles.add(salle);
        when(salleRepository.findByCriteria("Salle A", null)).thenReturn(salles);

        List<Salle> result = salleService.searchSalles("Salle A", null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Salle A", result.get(0).getNom());
    }

    @Test
    void getAllSalles_EmptyList() {
        when(salleRepository.findAll()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            salleService.getAllSalles();
        });

        assertEquals("Liste n'existe pas", exception.getMessage());
    }
}
