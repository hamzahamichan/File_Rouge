package com.feg.fileRouge.Services.servicces;

import com.feg.fileRouge.Entity.Dto.ServiceDto;
import com.feg.fileRouge.Entity.Model.Service;
import com.feg.fileRouge.Repository.RepositoryServices;
import com.feg.fileRouge.mapper.ServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
       Service service = this.mapper.toEntity(dto);
       Service service1= this.repositoryServices.save(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(service1);
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
    public Service getAllService() {
        return (Service) repositoryServices.findAll();
    }

    @Override
    public void delete(Long id) {
        this.repositoryServices.deleteById(id);
    }

}
