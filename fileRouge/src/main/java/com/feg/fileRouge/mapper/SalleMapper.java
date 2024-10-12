package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalleMapper{
    SallesDto toDto(Salle salle);

    @Mapping(source = "idClient", target = "client.idClient")
    Salle toEntity(SallesDto dto);
}
