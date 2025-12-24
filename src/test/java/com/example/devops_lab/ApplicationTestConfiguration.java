package com.example.devops_lab;

import com.example.devops_lab.dto.ApplicationEntityDto;
import com.example.devops_lab.entity.ApplicationEntity;

import java.util.UUID;

public class ApplicationTestConfiguration {

    public final static ApplicationEntity ENTITY;
    public final static ApplicationEntityDto DTO;

    public final static UUID ID = UUID.randomUUID();
    public final static String ENTITY_NAME = "Test entity name";
    public final static String ENTITY_DESCRIPTION = "Test entity description";

    static {
        ENTITY = new ApplicationEntity();
        ENTITY.setId(ID);
        ENTITY.setName(ENTITY_NAME);
        ENTITY.setDescription(ENTITY_DESCRIPTION);

        DTO = new ApplicationEntityDto();
        DTO.setId(ID);
        DTO.setName(ENTITY_NAME);
        DTO.setDescription(ENTITY_DESCRIPTION);
    }
}
