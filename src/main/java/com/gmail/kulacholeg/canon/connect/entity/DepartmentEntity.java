package com.gmail.kulacholeg.canon.connect.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Data
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int departmentCode;
    private String departmentName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    List<OperationEntity> operations;

}
