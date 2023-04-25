package com.gmail.kulacholeg.canon.connect.controller;

import com.gmail.kulacholeg.canon.connect.service.SettingsService;
import com.gmail.kulacholeg.canon.connect.util.PropertiesListCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService service;

    @Autowired
    public SettingsController(SettingsService service) {
        this.service = service;
    }

    @GetMapping
    public String getSettings(Model model){
        model.addAttribute("settings", service.getSettings());
        return "settings";
    }

    @GetMapping("/save")
    public String saveSettings(@RequestParam(name = "canon-ip") String ip,
                               @RequestParam(name = "receiver-email") String email,
                               @RequestParam(name = "request-time-pattern") String pattern,
                               RedirectAttributes redirectAttributes){
        service.setSettings(PropertiesListCreator.createList(ip, email,pattern));
        redirectAttributes.addFlashAttribute("message", "Налаштування збережено!");
        return "redirect:/settings";
    }
}
