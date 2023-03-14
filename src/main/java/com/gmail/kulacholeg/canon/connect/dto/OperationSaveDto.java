package com.gmail.kulacholeg.canon.connect.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.sql.Date;

@Getter
@Builder
@Jacksonized
public class OperationSaveDto {
    private Integer allOperations;
    private Integer copyBW;
    private Integer printBW;
    private Integer scanBW;
    private Integer scanColor;
    private Date date;
    private Long departmentId;
}
