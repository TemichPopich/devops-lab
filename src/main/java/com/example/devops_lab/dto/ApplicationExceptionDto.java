package com.example.devops_lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApplicationExceptionDto(@JsonProperty("cause") String message) {
}
