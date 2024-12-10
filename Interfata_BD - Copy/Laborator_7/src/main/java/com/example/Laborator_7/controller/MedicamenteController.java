package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Apartinator;
import com.example.Laborator_7.entity.Medic;
import com.example.Laborator_7.entity.Medicamente;
import com.example.Laborator_7.service.MedicamenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicamente")
public class MedicamenteController {

    @Autowired
    private MedicamenteService medicamenteService;

    @GetMapping
    public String getAllMedicamente(Model model) {
        List<Medicamente> medicamentes = medicamenteService.getAllMedicamente();
        model.addAttribute("medicamente", medicamentes);
        return "medicamente";
    }

    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/medicamente";
        }
        List<Medicamente> medicamente = medicamenteService.findByNume(nume);
        model.addAttribute("medicamente", medicamente);
        return "medicamente";
    }

    @PostMapping
    public int saveMedicamente(@RequestBody Medicamente medicamente) {
        return medicamenteService.saveMedicamente(medicamente);
    }
}
