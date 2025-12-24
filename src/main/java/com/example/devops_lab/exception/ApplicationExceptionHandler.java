package com.example.devops_lab.exception;

import com.example.devops_lab.dto.ApplicationExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({MissingRequestHeaderException.class, IllegalArgumentException.class})
    public ResponseEntity<ApplicationExceptionDto> handleMissingRequestHeaderException(final MissingRequestHeaderException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApplicationExceptionDto(ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionDto> handleApplicationException(final ApplicationException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ApplicationExceptionDto(ex.getMessage()));
    }

}
