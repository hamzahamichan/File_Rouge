package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.EquipementDto;
import com.feg.fileRouge.Entity.Model.Equipement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipementMapper {

    EquipementDto toDto(Equipement equipement);
    Equipement toEntity(EquipementDto equipementDto);

}
