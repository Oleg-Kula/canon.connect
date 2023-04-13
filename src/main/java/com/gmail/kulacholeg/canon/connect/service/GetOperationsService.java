package com.gmail.kulacholeg.canon.connect.service;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.entity.OperationEntity;
import com.gmail.kulacholeg.canon.connect.repository.OperationRepository;
import com.gmail.kulacholeg.canon.connect.util.OperationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetOperationsService {
    private final OperationRepository operationRepository;

    @Autowired
    public GetOperationsService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<OperationGetDto> getOperationsBetweenDates(String from, String to) {
        if (from == null || from.equals("")) from = LocalDate.now().toString();
        if (to == null || to.equals("")) to = LocalDate.now().toString();

        return operationRepository
                .getAllByDateBetween(Date.valueOf(from), Date.valueOf(to))
                .stream()
                .map(OperationDtoConverter::entityToDto)
                .collect(Collectors.groupingBy(OperationGetDto::getDepartmentName, TreeMap::new,
                        Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    String departmentName = entry.getKey();
                    List<OperationGetDto> operations = entry.getValue();
                    OperationGetDto max = operations.stream().max(Comparator.comparing(OperationGetDto::getAllOperations)).orElse(null);
                    OperationGetDto min = operations.stream().min(Comparator.comparing(OperationGetDto::getAllOperations)).orElse(null);

                    assert max != null;
                    return OperationGetDto.builder()
                            .allOperations(max.getAllOperations() - min.getAllOperations())
                            .copyBW(max.getCopyBW() - min.getCopyBW())
                            .printBW(max.getPrintBW() - min.getPrintBW())
                            .scanBW(max.getScanBW() - min.getScanBW())
                            .scanColor(max.getScanColor() - min.getScanColor())
                            .departmentName(departmentName)
                            .build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
