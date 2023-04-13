package com.gmail.kulacholeg.canon.connect.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class OperationGetDto {
    private Integer allOperations;
    private Integer copyBW;
    private Integer printBW;
    private Integer scanBW;
    private Integer scanColor;
    //private Date date;
    private String departmentName;
}
