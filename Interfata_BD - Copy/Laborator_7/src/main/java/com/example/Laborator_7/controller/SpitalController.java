package com.example.Laborator_7.controller;

import com.example.Laborator_7.dao.SpitalDAO;
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
    @Autowired
    private SpitalDAO spitalDAO;

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
    public String saveSpital(@ModelAttribute("spital") Spital spital, Model model) {
        try{
            spitalService.validateSpital(spital);
            spitalService.insertSpital(spital);
            return "redirect:/spitale";
        }catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "spital-form-insert";
        }
    }

    @GetMapping("/topSpitale")
    public String getTopSpitale(Model model) {
        List<Spital> topSpitale = spitalService.findTopSpitale();
        model.addAttribute("spitale", topSpitale);
        return "spitale";
    }

    @PostMapping("/update")
    public String updateSpital(@ModelAttribute("spital") Spital spital, Model model) {
        try{
            spitalService.validateSpital(spital);
            spitalService.updateSpital(spital);
            return "redirect:/spitale";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "spital-form";
        }
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
