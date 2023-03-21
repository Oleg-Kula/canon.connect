package com.gmail.kulacholeg.canon.connect.repository;

import com.gmail.kulacholeg.canon.connect.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> getAllByDate(Date date);

}
