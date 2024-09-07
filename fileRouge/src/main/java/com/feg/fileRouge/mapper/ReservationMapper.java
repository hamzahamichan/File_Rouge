package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.ReservationDto;
import com.feg.fileRouge.Entity.Model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDto toDto(Reservation reservation);

    @Mapping(source = "idSalle", target = "salle.idSalle")  // Ajout du mapping pour idSalle
    Reservation toEntity(ReservationDto dto);
}

