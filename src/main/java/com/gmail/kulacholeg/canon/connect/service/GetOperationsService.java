package com.gmail.kulacholeg.canon.connect.service;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.repository.OperationRepository;
import com.gmail.kulacholeg.canon.connect.util.OperationsCalculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class GetOperationsService {
    private final OperationRepository operationRepository;

    @Autowired
    public GetOperationsService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<OperationGetDto> getOperationsPerDay(String day){
        return OperationsCalculate.calculate(operationRepository.getOperationsPerDate(Date.valueOf(day)));
    }

    public List<OperationGetDto> getOperationsBetweenDates(String from, String to) {

        return OperationsCalculate.calculate(operationRepository.getAllByDateBetween(
                Date.valueOf(from),
                Date.valueOf(to)
        ));
    }
}
