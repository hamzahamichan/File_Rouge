package com.feg.fileRouge.Services.servicces;

import com.feg.fileRouge.Entity.Dto.ServiceDto;
import com.feg.fileRouge.Entity.Model.Service;
import com.feg.fileRouge.Repository.RepositoryServices;
import com.feg.fileRouge.mapper.ServiceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ServicesserviceImpTest {

    @Mock
    private RepositoryServices repositoryServices;

    @Mock
    private ServiceMapper mapper;

    @InjectMocks
    private ServicesserviceImp service;

    private ServiceDto serviceDto;
    private Service serviceEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceDto = new ServiceDto(); // Assurez-vous de créer un ServiceDto valide ici
        serviceEntity = new Service(); // Assurez-vous de créer un Service valide ici
    }

    @Test
    void testAddService() {
        // Arrange
        when(mapper.toEntity(serviceDto)).thenReturn(serviceEntity);
        when(repositoryServices.save(any(Service.class))).thenReturn(serviceEntity);

        // Act
        ResponseEntity<Service> response = service.addService(serviceDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(serviceEntity, response.getBody());

        // Vérifier que mapper.toEntity a été appelé une fois
        verify(mapper, times(1)).toEntity(serviceDto);
        // Vérifier que repositoryServices.save a été appelé une fois
        verify(repositoryServices, times(1)).save(serviceEntity);
    }

    @Test
    void testUpdateService() {
        // Arrange
        when(mapper.toEntity(serviceDto)).thenReturn(serviceEntity);
        when(repositoryServices.save(any(Service.class))).thenReturn(serviceEntity);

        // Act
        ServiceDto updatedService = service.UpdateService(serviceDto);

        // Assert
        assertEquals(serviceDto, updatedService);

        // Vérifier que mapper.toEntity a été appelé une fois
        verify(mapper, times(1)).toEntity(serviceDto);
        // Vérifier que repositoryServices.save a été appelé une fois
        verify(repositoryServices, times(1)).save(serviceEntity);
    }

    @Test
    void testDeleteService() {
        // Arrange
        Long serviceId = 1L;

        // Act
        service.delete(serviceId);

        // Assert
        // Vérifier que deleteById a été appelé une fois avec l'ID spécifié
        verify(repositoryServices, times(1)).deleteById(serviceId);
    }

    // Ajoutez d'autres tests pour les autres méthodes si nécessaire
}
