package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Boli_Asociate;
import com.example.Laborator_7.service.Boli_AsociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boli_asociate")
public class Boli_AsociateController {

    @Autowired
    private Boli_AsociateService boli_AsociateService;

    @GetMapping
    public String getAllBoli(Model model) {
        List<Boli_Asociate> boliAsociate = boli_AsociateService.getAllBoliAsociate();
        model.addAttribute("boliAsociate", boliAsociate);
        return "boli_asociate";
    }

    @PostMapping
    public int saveBoli(@RequestBody Boli_Asociate boliAsociate) {
        return boli_AsociateService.insertBoalaAsociata(boliAsociate);
    }

    @GetMapping("/new")
    public String showNewFormBoliAsociate(Model model) {
        Boli_Asociate boli = new Boli_Asociate();
        model.addAttribute("boli", boli);
        return "boli_asociate-form-insert";
    }

    @PostMapping("/save")
    public String saveBoalaAsociata(@ModelAttribute("boala") Boli_Asociate boala) {
        boli_AsociateService.insertBoalaAsociata(boala);
        return "redirect:/boli_asociate";
    }

    @PostMapping("/update")
    public String updateBoalaAsociata(@ModelAttribute("boala") Boli_Asociate boala) {
        boli_AsociateService.updateBoalaAsociata(boala);
        return "redirect:/boli_asociate";
    }

    @GetMapping("/edit/{id}")
    public String showEditFormBoliAsociate(@PathVariable("id") int id, Model model) {
        Boli_Asociate boala = boli_AsociateService.findById(id);
        model.addAttribute("boala", boala);
        return "/boli_asociate-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoalaAsociata(@PathVariable("id") int id) {
        boli_AsociateService.deleteBoalaAsociata(id);
        return "redirect:/boli_asociate";
    }

    @GetMapping("/search")
    public String findByNumeBoalaAsociata(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/boli_asociate";
        }
        List<Boli_Asociate> boliAsociate = boli_AsociateService.findByNume(nume);
        model.addAttribute("boliAsociate", boliAsociate);
        return "boli_asociate";
    }
}
