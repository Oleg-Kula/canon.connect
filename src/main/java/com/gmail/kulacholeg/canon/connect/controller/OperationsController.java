package com.gmail.kulacholeg.canon.connect.controller;

import com.gmail.kulacholeg.canon.connect.service.GetOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/operations")
public class OperationsController {

    private final GetOperationsService service;

    @Autowired
    public OperationsController(GetOperationsService service) {
        this.service = service;
    }

    @GetMapping
    public String getOperations(Model model){
        model.addAttribute("operations", service.getOperationsPerDate("2023-03-23"));
        return "oper";
    }
}
