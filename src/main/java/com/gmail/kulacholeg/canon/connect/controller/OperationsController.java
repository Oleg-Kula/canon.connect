package com.gmail.kulacholeg.canon.connect.controller;

import com.gmail.kulacholeg.canon.connect.dto.OperationSaveDto;
import com.gmail.kulacholeg.canon.connect.service.OperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final OperationsService service;

    @Autowired
    public OperationsController(OperationsService service) {
        this.service = service;
    }

    @GetMapping
    public List<OperationSaveDto> getOperations(){
        return service.getAllOperations();
    }
}
