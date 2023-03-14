package com.gmail.kulacholeg.canon.connect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "printer_operations")
@NoArgsConstructor
@Data
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer allOperations;
    private Integer copyBW;
    private Integer printBW;
    private Integer scanBW;
    private Integer scanColor;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
}
