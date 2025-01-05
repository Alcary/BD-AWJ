package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Apartinator;
import com.example.Laborator_7.entity.CompaniiFarmaceutice;
import com.example.Laborator_7.service.CompaniiFarmaceuticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companii_farmaceutice")
public class CompaniiFarmaceuticeController {

    @Autowired
    private CompaniiFarmaceuticeService companiiFarmaceuticeService;

    @GetMapping
    public String getAllCompanii(Model model) {
        List<CompaniiFarmaceutice> companii = companiiFarmaceuticeService.getAllCompanii();
        model.addAttribute("companii", companii);
        return "companii_farmaceutice";
    }

    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/companii_farmaceutice";
        }
        List<CompaniiFarmaceutice> companii = companiiFarmaceuticeService.findByNume(nume);
        model.addAttribute("companii", companii);
        return "companii_farmaceutice";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        CompaniiFarmaceutice companie = new CompaniiFarmaceutice();
        model.addAttribute("companie", companie);
        return "companie-form-insert";
    }

    @PostMapping("/save")
    public String saveCompanie(@ModelAttribute("companie") CompaniiFarmaceutice companie) {
        companiiFarmaceuticeService.insertCompanie(companie);
        return "redirect:/companii_farmaceutice";
    }

    @PostMapping("/update")
    public String updateCompanie(@ModelAttribute("companie") CompaniiFarmaceutice companie) {
        companiiFarmaceuticeService.updateCompanie(companie);
        return "redirect:/companii_farmaceutice";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        CompaniiFarmaceutice companie = companiiFarmaceuticeService.findById(id);
        model.addAttribute("companie", companie);
        return "companie-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompanie(@PathVariable("id") int id) {
        companiiFarmaceuticeService.deleteCompanie(id);
        return "redirect:/companii_farmaceutice";
    }
}
