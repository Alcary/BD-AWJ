package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Apartinator;
import com.example.Laborator_7.entity.Medic;
import com.example.Laborator_7.entity.Medicamente;
import com.example.Laborator_7.entity.Spital;
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

    @GetMapping("/new")
    public String showNewForm(Model model) {
        Medicamente medicamente = new Medicamente();
        model.addAttribute("medicamente", medicamente);
        return "medicamente-form-insert";
    }

    @PostMapping("/save")
    public String saveMedicament(@ModelAttribute("medicamente") Medicamente medicamente) {
        medicamenteService.insertMedicament(medicamente);
        return "redirect:/medicamente";
    }

    @PostMapping("/update")
    public String updateMedicament(@ModelAttribute("medicamente") Medicamente medicamente) {
        medicamenteService.updateMedicament(medicamente);
        return "redirect:/medicamente";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Medicamente medicamente = medicamenteService.findById(id);
        model.addAttribute("medicamente", medicamente);
        return "medicamente-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicament(@PathVariable("id") int id) {
        medicamenteService.deleteMedicament(id);
        return "redirect:/medicamente";
    }
}
