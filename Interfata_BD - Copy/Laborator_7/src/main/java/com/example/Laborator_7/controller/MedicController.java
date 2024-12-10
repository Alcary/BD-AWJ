package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Apartinator;
import com.example.Laborator_7.entity.Medic;
import org.springframework.stereotype.Controller;
import com.example.Laborator_7.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medici")
public class MedicController {

    @Autowired
    private MedicService medicService;

    @GetMapping
    public String getAllMedics(Model model) {
        List<Medic> medici = medicService.getAllMedics();
        model.addAttribute("medici", medici);
        return "medici";
    }

    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/medici";
        }
        List<Medic> medici = medicService.findByNume(nume);
        model.addAttribute("medici", medici);
        return "medici";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        Medic medic = new Medic();
        model.addAttribute("medic", medic);
        return "medic-form-insert";
    }

    @PostMapping("/save")
    public String saveMedic(@ModelAttribute("medic") Medic medic) {
        medicService.insertMedic(medic);
        return "redirect:/medici";
    }

    @PostMapping("/update")
    public String updateMedic(@ModelAttribute("medic") Medic medic) {
        medicService.updateMedic(medic);
        return "redirect:/medici";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Medic medic = medicService.findById(id);
        model.addAttribute("medic", medic);
        return "medic-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedic(@PathVariable("id") int id) {
        medicService.deleteMedic(id);
        return "redirect:/medici";
    }
}
