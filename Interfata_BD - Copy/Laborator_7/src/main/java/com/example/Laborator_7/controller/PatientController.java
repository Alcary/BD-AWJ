package com.example.Laborator_7.controller;

import com.example.Laborator_7.dao.PatientDAO;
import com.example.Laborator_7.entity.Patient;
import com.example.Laborator_7.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pacienti")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDAO pacientDAO;

    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/pacienti";
        }
        List<Patient> pacienti = patientService.findByNume(nume);
        model.addAttribute("pacienti", pacienti);
        return "pacienti";
    }

    @GetMapping
    public String getAllPatients(Model model) {
        List<Patient> pacienti = patientService.getAllPatients();
        model.addAttribute("pacienti", pacienti);
        return "pacienti";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        Patient pacient = new Patient();
        model.addAttribute("pacient", pacient);
        return "pacient-form-insert";
    }

    @PostMapping("/save")
    public String savePacient(@ModelAttribute("pacient") Patient pacient) {
        patientService.insertPacient(pacient);
        return "redirect:/pacienti";
    }

//    @PostMapping("/save")
//    public String savePatientWithDetails(
//            @ModelAttribute("patient") Patient patient,
//            @RequestParam(value = "medicamente", required = false) List<Integer> medicamente,
//            @RequestParam(value = "boli", required = false) List<Integer> boli,
//            @RequestParam(value = "dataaparitie", required = false) Date dataaparitie,
//            @RequestParam(value = "datastarttratament", required = false) Date datastarttratament) {
//        patientService.insertPacientCuDetalii(patient, medicamente, boli, dataaparitie, datastarttratament);
//        return "redirect:/pacienti";
//    }

    @PostMapping("/update")
    public String updatePacient(@ModelAttribute("pacient") Patient pacient) {
        patientService.updatePacient(pacient);
        return "redirect:/pacienti";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Patient pacient = patientService.findById(id);
        model.addAttribute("pacient", pacient);
        return "pacient-form";
    }

    @GetMapping("/delete/{id}")
    public String deletePacient(@PathVariable("id") int id) {
        patientService.deletePacient(id);
        return "redirect:/pacienti";
    }

    @GetMapping("/detalii/{id}")
    public String detaliiPacient(@PathVariable int id, Model model) {
        List<Map<String, Object>> detalii = pacientDAO.obtineDetaliiMedicale(id);
        model.addAttribute("detalii", detalii);
        return "pacient-detalii";
    }

    @GetMapping("/istoric/{id}")
    public String istoricPacient(@PathVariable int id, Model model) {
        List<Map<String, Object>> detalii = pacientDAO.obtineIstoricMedical(id);
        model.addAttribute("detalii", detalii);
        return "pacient-istoric";
    }
}
