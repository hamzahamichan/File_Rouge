package com.feg.fileRouge.Services.servicces;

import com.feg.fileRouge.Entity.Dto.ServiceDto;
import com.feg.fileRouge.Entity.Model.Service;
import com.feg.fileRouge.Repository.RepositoryServices;
import com.feg.fileRouge.mapper.ServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@org.springframework.stereotype.Service

public class ServicesserviceImp implements ServicesService {

    private final RepositoryServices repositoryServices;
    private final ServiceMapper mapper;

    public ServicesserviceImp(RepositoryServices repositoryServices, ServiceMapper serviceMapper, ServiceMapper mapper) {
        this.repositoryServices = repositoryServices;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Service> addService(ServiceDto dto) {
        // Utilisez le mapper pour convertir le ServiceDto en entité Service
        Service service = this.mapper.toEntity(dto);

        // Sauvegardez l'entité dans la base de données
        Service savedService = this.repositoryServices.save(service);

        // Retournez une réponse avec le service sauvegardé
        return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }

    @Override
    public ServiceDto findServiceId(Long id) {
        return null;
    }

    @Override
    public ServiceDto getServiceByName(String nom) {
        return null;
    }

    @Override
    public ServiceDto UpdateService(ServiceDto dto) {
        Service service = this.mapper.toEntity(dto);
        Service service1 = this.repositoryServices.save(service);
        return dto;
    }

    @Override
    public List<Service> getAllService() {
        try {
            return  this.repositoryServices.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        this.repositoryServices.deleteById(id);
    }

}
