package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Apartinator;
import com.example.Laborator_7.entity.Medic;
import com.example.Laborator_7.entity.Spital;
import com.example.Laborator_7.service.SpitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/spitale")
public class SpitalController {

    @Autowired
    private SpitalService spitalService;

    @GetMapping
    public String getAllSpitale(Model model) {
        List<Spital> spitale = spitalService.getAllHosptials();
        model.addAttribute("spitale", spitale);
        return "spitale";
    }

    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/spitale";
        }
        List<Spital> spitale = spitalService.findByNume(nume);
        model.addAttribute("spitale", spitale);
        return "spitale";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        Spital spital = new Spital();
        model.addAttribute("spital", spital);
        return "spital-form-insert";
    }

    @PostMapping("/save")
    public String saveSpital(@ModelAttribute("spital") Spital spital) {
        spitalService.insertSpital(spital);
        return "redirect:/spitale";
    }

    @PostMapping("/update")
    public String updateSpital(@ModelAttribute("spital") Spital spital) {
        spitalService.updateSpital(spital);
        return "redirect:/spitale";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Spital spital = spitalService.findById(id);
        model.addAttribute("spital", spital);
        return "spital-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSpital(@PathVariable("id") int id) {
        spitalService.deleteSpital(id);
        return "redirect:/spitale";
    }
}
