package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.ClientDto;
import com.feg.fileRouge.Entity.Model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "motDePasse", ignore = true)
    ClientDto toDto(Client client);
    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "motDePasse", ignore = true)
    void updateEntityFromDto(ClientDto dto, @MappingTarget Client entity);
}
