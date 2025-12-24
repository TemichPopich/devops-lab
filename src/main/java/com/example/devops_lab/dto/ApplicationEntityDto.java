package com.example.devops_lab.dto;

import com.example.devops_lab.entity.ApplicationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationEntityDto {

    @JsonIgnore
    private UUID id;
    private String name;
    private String description;
    private ApplicationEntity.EntityType type;

}
