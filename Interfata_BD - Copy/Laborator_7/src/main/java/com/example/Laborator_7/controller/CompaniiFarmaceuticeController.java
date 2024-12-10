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

    @PostMapping
    public int saveCompanii(@RequestBody CompaniiFarmaceutice companie) {
        return companiiFarmaceuticeService.saveCompanie(companie);
    }
}
