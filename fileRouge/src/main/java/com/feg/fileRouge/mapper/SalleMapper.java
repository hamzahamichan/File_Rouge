package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalleMapper{
    SallesDto toDto(Salle salle);
    Salle toEntity(SallesDto dto);
}
