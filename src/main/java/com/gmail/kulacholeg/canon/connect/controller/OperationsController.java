package com.gmail.kulacholeg.canon.connect.controller;

import com.gmail.kulacholeg.canon.connect.service.GetOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = {"/", "/operations"})
public class OperationsController {

    private final GetOperationsService service;

    @Autowired
    public OperationsController(GetOperationsService service) {
        this.service = service;
    }

    @GetMapping
    public String getOperations(){
        return "oper";
    }

    @GetMapping("/search")
    public String  getOperationsBetweenDates(@RequestParam String from,
                                             @RequestParam String to,
                                             Model model){
        model.addAttribute("operations", service.getOperationsBetweenDates(from, to));
        return "oper";
    }
}
