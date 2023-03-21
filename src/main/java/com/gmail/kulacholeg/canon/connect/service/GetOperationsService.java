package com.gmail.kulacholeg.canon.connect.service;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.entity.OperationEntity;
import com.gmail.kulacholeg.canon.connect.repository.OperationRepository;
import com.gmail.kulacholeg.canon.connect.util.OperationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetOperationsService {
    private final OperationRepository operationRepository;

    @Autowired
    public GetOperationsService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<OperationGetDto> getOperationsPerDate(String date){
        return operationRepository.getAllByDate(Date.valueOf(date)).stream()
                .map(OperationDtoConverter::entityToDto)
                .toList();
    }
}
