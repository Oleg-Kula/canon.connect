package com.gmail.kulacholeg.canon.connect.repository;

import com.gmail.kulacholeg.canon.connect.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    DepartmentEntity findByDepartmentCode(int code);
}
