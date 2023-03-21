package com.gmail.kulacholeg.canon.connect.util;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.dto.OperationSaveDto;
import com.gmail.kulacholeg.canon.connect.entity.OperationEntity;
import com.gmail.kulacholeg.canon.connect.repository.DepartmentRepository;

public class OperationDtoConverter {
    public static OperationEntity dtoToEntity(OperationSaveDto dto, DepartmentRepository repository){
        OperationEntity entity = new OperationEntity();
        entity.setAllOperations(dto.getAllOperations());
        entity.setCopyBW(dto.getCopyBW());
        entity.setPrintBW(dto.getPrintBW());
        entity.setScanBW(dto.getScanBW());
        entity.setScanColor(dto.getScanColor());
        entity.setDate(dto.getDate());
        entity.setDepartment(repository.findByDepartmentCode(dto.getDepartmentCode()));

        return entity;
    }

    public static OperationGetDto entityToDto(OperationEntity entity){
        return OperationGetDto.builder()
                .allOperations(entity.getAllOperations())
                .copyBW(entity.getCopyBW())
                .printBW(entity.getPrintBW())
                .scanBW(entity.getScanBW())
                .scanColor(entity.getScanColor())
                .date(entity.getDate())
                .departmentName(entity.getDepartment().getDepartmentName())
                .build();
    }
}
