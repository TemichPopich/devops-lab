package com.example.devops_lab.mapper;

import com.example.devops_lab.dto.ApplicationEntityDto;
import com.example.devops_lab.entity.ApplicationEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static com.example.devops_lab.ApplicationTestConfiguration.DTO;
import static com.example.devops_lab.ApplicationTestConfiguration.ENTITY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ApplicationEntityMapperTest {

    private final ApplicationEntityMapper mapper = Mappers.getMapper(ApplicationEntityMapper.class);

    @ParameterizedTest
    @MethodSource("entityToDtoDataProvider")
    void testEntityToDtoMapping(final ApplicationEntity input, final ApplicationEntityDto expected) {

        // Arrange & Act

        final ApplicationEntityDto result = mapper.toDto(input);

        // Assert

        assertNotSame(result, expected);
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @MethodSource("dtoToEntityDataProvider")
    void testDtoToEntityMapping(final ApplicationEntityDto input, final ApplicationEntity expected) {

        // Arrange & Act

        final ApplicationEntity result = mapper.toEntity(input);

        // Assert

        assertNotSame(result, expected);
        assertEquals(result, expected);
    }

    private static Stream<Arguments> entityToDtoDataProvider() {
        return Stream.of(Arguments.of(ENTITY, DTO));
    }

    private static Stream<Arguments> dtoToEntityDataProvider() {
        return Stream.of(Arguments.of(DTO, ENTITY));
    }
}
