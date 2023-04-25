package com.gmail.kulacholeg.canon.connect.service;

import com.gmail.kulacholeg.canon.connect.entity.PropertyEntity;
import com.gmail.kulacholeg.canon.connect.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SettingsService {
    private final SettingsRepository settingsRepository;

    @Autowired
    public SettingsService(SettingsRepository settingsRepository){
        this.settingsRepository = settingsRepository;
    }

    public void setSettings(List<PropertyEntity> newSettings){
       settingsRepository.saveAll(newSettings);
    }

    public Map<String, String> getSettings(){
        return settingsRepository.findAll().stream()
                .collect(Collectors.toMap(PropertyEntity::getName, PropertyEntity::getValue));
    }
}
