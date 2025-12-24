package com.example.devops_lab.service;

import com.example.devops_lab.dto.ApplicationEntityDto;
import com.example.devops_lab.entity.ApplicationEntity;
import com.example.devops_lab.exception.ApplicationException;
import com.example.devops_lab.mapper.ApplicationEntityMapper;
import com.example.devops_lab.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationEntityMapper mapper;
    private final ApplicationRepository repository;

    public ApplicationEntityDto get(final UUID id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(
                        () -> new ApplicationException("Entity not found", HttpStatus.BAD_REQUEST));
    }

    public ApplicationEntityDto post(final ApplicationEntityDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ApplicationEntityDto put(final UUID id, final ApplicationEntityDto dto) {
        final ApplicationEntity entity = repository.findById(id)
                .orElseThrow(
                        () -> new ApplicationException("Entity not found", HttpStatus.BAD_REQUEST));

        mapper.updateEntity(entity, dto);

        return mapper.toDto(repository.save(entity));
    }

    public void delete(final UUID id) {
        final ApplicationEntity entity = repository.findById(id)
                .orElseThrow(
                        () -> new ApplicationException("Entity not found", HttpStatus.BAD_REQUEST));
        repository.delete(entity);
    }

}
