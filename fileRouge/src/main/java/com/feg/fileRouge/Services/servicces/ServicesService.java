package com.feg.fileRouge.Services.servicces;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Dto.ServiceDto;
import com.feg.fileRouge.Entity.Model.Service;
import org.springframework.http.ResponseEntity;

public interface ServicesService {
    ResponseEntity<Service> addService(ServiceDto service);
    ServiceDto findServiceId(Long id);
    ServiceDto getServiceByName(String nom);
    ServiceDto UpdateService(ServiceDto dto);
    Service getAllService();
     void delete(Long id);
}
