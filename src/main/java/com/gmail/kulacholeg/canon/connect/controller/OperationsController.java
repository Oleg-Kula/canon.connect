package com.gmail.kulacholeg.canon.connect.controller;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.dto.OperationSaveDto;
import com.gmail.kulacholeg.canon.connect.service.GetOperationsService;
import com.gmail.kulacholeg.canon.connect.service.SaveOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final GetOperationsService service;

    @Autowired
    public OperationsController(GetOperationsService service) {
        this.service = service;
    }

    @GetMapping
    public List<OperationGetDto> getOperations(){
        return service.getOperationsPerDate("2023-03-21");
    }
}
