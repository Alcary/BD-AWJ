package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Pacienti_Boli;
import com.example.Laborator_7.service.Pacienti_BoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacienti_boli")
public class Pacienti_BoliController {

    @Autowired
    private Pacienti_BoliService pacienti_BoliService;

    @GetMapping
    public String getAllPacientiBoli(Model model) {
        List<Pacienti_Boli> pacientiBoli = pacienti_BoliService.getAllPacientiBoli();
        model.addAttribute("pacientiBoli", pacientiBoli);
        return "pacienti_boli";
    }

    @PostMapping
    public int saveBoli(@RequestBody Pacienti_Boli pacientiBoli) {
        return pacienti_BoliService.savePacientBoala(pacientiBoli);
    }
}
