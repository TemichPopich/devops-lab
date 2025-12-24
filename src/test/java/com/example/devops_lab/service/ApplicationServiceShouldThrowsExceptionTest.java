package com.example.devops_lab.service;

import com.example.devops_lab.exception.ApplicationException;
import com.example.devops_lab.mapper.ApplicationEntityMapperImpl;
import com.example.devops_lab.repository.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.example.devops_lab.ApplicationTestConfiguration.DTO;
import static com.example.devops_lab.ApplicationTestConfiguration.ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationService.class, ApplicationEntityMapperImpl.class})
public class ApplicationServiceShouldThrowsExceptionTest {

    private static final String ERROR_MESSAGE = "Entity not found";

    @Autowired
    private ApplicationService applicationService;

    @MockBean
    private ApplicationRepository applicationRepository;

    @BeforeEach
    void setup() {
        when(applicationRepository.findById(eq(ID))).thenReturn(Optional.empty());
    }

    @Test
    void shouldThrowExceptionWhenEntityNotFoundWhileExecutingGet() {

        // Arrange & Act & Assert

        final ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> applicationService.get(ID)
        );

        verify(applicationRepository).findById(eq(ID));
        assertEquals(exception.getMessage(), ERROR_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenEntityNotFoundWhileExecutingPost() {

        // Arrange & Act & Assert

        assertDoesNotThrow(() -> applicationService.post(DTO));

        verify(applicationRepository, times(0)).findById(eq(ID));
    }

    @Test
    void shouldThrowExceptionWhenEntityNotFoundWhileExecutingPut() {

        // Arrange & Act & Assert

        final ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> applicationService.put(ID, DTO)
        );

        verify(applicationRepository).findById(eq(ID));
        assertEquals(exception.getMessage(), ERROR_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenEntityNotFoundWhileExecutingDelete() {

        // Arrange & Act & Assert

        final ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> applicationService.delete(ID)
        );

        verify(applicationRepository).findById(eq(ID));
        assertEquals(exception.getMessage(), ERROR_MESSAGE);
    }
}
