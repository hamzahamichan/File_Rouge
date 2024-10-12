package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.ServiceDto;
import com.feg.fileRouge.Entity.Model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDto toDto(Service service);

    Service toEntity(ServiceDto dto);
}
