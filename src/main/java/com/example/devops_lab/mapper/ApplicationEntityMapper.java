package com.example.devops_lab.mapper;

import com.example.devops_lab.dto.ApplicationEntityDto;
import com.example.devops_lab.entity.ApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationEntityMapper {

    ApplicationEntityDto toDto(final ApplicationEntity applicationEntity);

    ApplicationEntity toEntity(final ApplicationEntityDto applicationEntityDto);

    void updateEntity(final @MappingTarget ApplicationEntity applicationEntity, final ApplicationEntityDto applicationEntityDto);

}
