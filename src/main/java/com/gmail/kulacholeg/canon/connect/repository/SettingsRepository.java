package com.gmail.kulacholeg.canon.connect.repository;

import com.gmail.kulacholeg.canon.connect.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SettingsRepository extends JpaRepository<PropertyEntity, String> {
    PropertyEntity getByName(String name);

}
