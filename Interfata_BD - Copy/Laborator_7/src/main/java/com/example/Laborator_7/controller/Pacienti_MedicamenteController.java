package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Pacienti_Medicamente;
import com.example.Laborator_7.service.Pacienti_MedicamenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacienti_medicamente")
public class Pacienti_MedicamenteController {

    @Autowired
    private Pacienti_MedicamenteService pacientiMedicamenteService;

    @GetMapping
    public String getAllPacientiMedicamente(Model model) {
        List<Pacienti_Medicamente> pacientiMedicamente = pacientiMedicamenteService.getAllPacientiMedicamente();
        model.addAttribute("pacientiMedicamente", pacientiMedicamente);
        return "pacienti_medicamente";
    }

    @PostMapping
    public int savePacientiMedicamente(@RequestBody Pacienti_Medicamente pacientiMedicamente) {
        return pacientiMedicamenteService.savePacientMedicament(pacientiMedicamente);
    }
}
