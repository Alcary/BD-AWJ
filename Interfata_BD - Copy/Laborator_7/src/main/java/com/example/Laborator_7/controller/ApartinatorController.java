package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Apartinator;
import com.example.Laborator_7.service.ApartinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/apartinatori")
public class ApartinatorController {

    @Autowired
    private ApartinatorService apartinatorService;

    @GetMapping
    public String getAllApartinatori(Model model) {
        List<Apartinator> apartinatori = apartinatorService.getAllApartinatori();
        model.addAttribute("apartinatori", apartinatori);
        return "apartinatori";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        Apartinator apartinator = new Apartinator();
        model.addAttribute("apartinator", apartinator);
        return "apartinator-form-insert";
    }

    @PostMapping("/save")
    public String saveApartinator(@ModelAttribute("apartinator") Apartinator apartinator) {
        apartinatorService.insertApartinator(apartinator);
        return "redirect:/apartinatori";
    }

    @PostMapping("/update")
    public String updateApartinator(@ModelAttribute("apartinator") Apartinator apartinator) {
        apartinatorService.updateApartinator(apartinator);
        return "redirect:/apartinatori";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Apartinator apartinator = apartinatorService.findById(id);
        model.addAttribute("apartinator", apartinator);
        return "apartinator-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteApartinator(@PathVariable("id") int id) {
        apartinatorService.deleteApartinator(id);
        return "redirect:/apartinatori";
    }

    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/apartinatori";
        }
        List<Apartinator> apartinatori = apartinatorService.findByNume(nume);
        model.addAttribute("apartinatori", apartinatori);
        return "apartinatori";
    }
}
