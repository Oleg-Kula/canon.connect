package com.gmail.kulacholeg.canon.connect.repository;

import com.gmail.kulacholeg.canon.connect.entity.DepartmentEntity;
import com.gmail.kulacholeg.canon.connect.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    List<OperationEntity> getAllByDateBetween(Date from, Date to);

    OperationEntity getOperationEntityByDepartmentAndDate(DepartmentEntity department, Date date);

    @Query(value =
            "SELECT * FROM printer_operations " +
                    "WHERE date BETWEEN " +
            "(SELECT MAX(date) FROM printer_operations " +
                    "WHERE date < :date) AND :date",
            nativeQuery = true)
    List<OperationEntity> getOperationsPerDate(@Param("date") Date date);

}
