package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.AvisDto;
import com.feg.fileRouge.Entity.Model.Avis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AvisMapper {

    AvisDto toDto(Avis avis);

    Avis toEnyity(AvisDto dto);
}
