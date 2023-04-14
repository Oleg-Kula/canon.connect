package com.gmail.kulacholeg.canon.connect.util;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.entity.OperationEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OperationsCalculate {

    public static List<OperationGetDto> calculate(List<OperationEntity> operationEntities){
        return operationEntities.stream()
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
