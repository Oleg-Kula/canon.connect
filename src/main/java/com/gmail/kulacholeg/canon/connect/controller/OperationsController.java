package com.gmail.kulacholeg.canon.connect.controller;

import com.gmail.kulacholeg.canon.connect.service.GetOperationsService;
import com.gmail.kulacholeg.canon.connect.service.SaveOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/operations")
public class OperationsController {

    private final GetOperationsService operationsService;
    private final SaveOperationsService saveService;


    @Autowired
    public OperationsController(GetOperationsService operationsService, SaveOperationsService saveService) {
        this.saveService = saveService;
        this.operationsService = operationsService;
    }

    @GetMapping
    public String getOperations(){
        return "oper";
    }

    @GetMapping("/search_between")
    public String  getOperationsBetweenDates(@RequestParam String from,
                                             @RequestParam String to,
                                             Model model){
        model.addAttribute("operations", operationsService.getOperationsBetweenDates(from, to));
        return "oper";
    }

    @GetMapping("/upload")
    public String forceUploadOperations(RedirectAttributes redirectAttributes){
        saveService.getOperationsAndSave();
        redirectAttributes.addFlashAttribute("message", "Базу синхронізовано!");
        return "redirect:/operations";
    }

    @GetMapping("/search_day")
    public String getOperationsPerDay(@RequestParam String day, Model model){
        model.addAttribute("operations", operationsService.getOperationsPerDay(day));
        return "oper";
    }
}
