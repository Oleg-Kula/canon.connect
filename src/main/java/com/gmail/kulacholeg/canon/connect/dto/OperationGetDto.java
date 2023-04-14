package com.gmail.kulacholeg.canon.connect.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
