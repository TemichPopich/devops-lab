package com.example.devops_lab.controller;

import com.example.devops_lab.dto.ApplicationEntityDto;
import com.example.devops_lab.service.ApplicationService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1")
public class ApplicationController {

    private final ApplicationService service;
    private final Counter applicationCounter;

    @Timed
    @GetMapping("get/{id}")
    public ResponseEntity<ApplicationEntityDto> get(@PathVariable final UUID id) {
        applicationCounter.increment();
        return ResponseEntity.ok(service.get(id));
    }

    @Timed
    @PostMapping("post")
    public ResponseEntity<ApplicationEntityDto> post(@RequestBody final ApplicationEntityDto body) {
        applicationCounter.increment();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.post(body));
    }

    @Timed
    @PutMapping("put/{id}")
    public ResponseEntity<ApplicationEntityDto> put(@PathVariable final UUID id,
                                                    @RequestBody final ApplicationEntityDto body) {
        applicationCounter.increment();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.put(id, body));
    }

    @Timed
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        applicationCounter.increment();
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
