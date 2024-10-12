package com.feg.fileRouge.Services.servicces;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Dto.ServiceDto;
import com.feg.fileRouge.Entity.Model.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServicesService {
    ResponseEntity<Service> addService(ServiceDto dto);
    ServiceDto findServiceId(Long id);
    ServiceDto getServiceByName(String nom);
    ServiceDto UpdateService(ServiceDto dto);
    List<Service> getAllService();
     void delete(Long id);
}
