/** Este un controller care se ocupa de gestionarea cererilor legate de Pacient
 * @author Calaras Alexandru
 * @version 8 Ianuarie 2025
 */
package com.example.Laborator_7.controller;

import com.example.Laborator_7.dao.PacientDAO;
import com.example.Laborator_7.entity.Pacient;
import com.example.Laborator_7.service.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pacienti")
public class PacientController {

    @Autowired
    private PacientService pacientService;

    @Autowired
    private PacientDAO pacientDAO;

    //Cauta pacienti dupa nume si afiseaza rezultatele
    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/pacienti";
        }
        List<Pacient> pacienti = pacientService.findByNume(nume);
        model.addAttribute("pacienti", pacienti);
        return "pacienti";
    }

    //Cauta pacienti care au o boala specifica
    @GetMapping("/searchBoala")
    public String getPatientsWithDisease(@RequestParam("boala") String boala, Model model) {
        if(boala == null || boala.isEmpty()) {
            return "redirect:/pacienti";
        }
        List<Pacient> pacienti = pacientService.findPacientBoala(boala);
        model.addAttribute("pacienti", pacienti);
        return "pacienti";
    }

    //Returneaza toti pacientii si ii afiseaza intr-o lista pe pagina web
    @GetMapping
    public String getAllPatients(Model model) {
        List<Pacient> pacienti = pacientService.getAllPatients();
        model.addAttribute("pacienti", pacienti);
        return "pacienti";
    }

    //Afiseaza formularul pentru adaugarea unui pacient nou
    @GetMapping("/new")
    public String showNewForm(Model model) {
        Pacient pacient = new Pacient();
        model.addAttribute("pacient", pacient);
        return "pacient-form-insert";
    }

    //Proceseaza salvarea unui pacient nou
    @PostMapping("/save")
    public String savePacient(@ModelAttribute("pacient") Pacient pacient, Model model) {
        try {
            pacientService.validatePacient(pacient);
            pacientService.insertPacient(pacient);
            return "redirect:/pacienti";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pacient-form-insert";
        }
    }

    //Proceseaza actualizarea unui pacient existent
    @PostMapping("/update")
    public String updatePacient(@ModelAttribute("pacient") Pacient pacient, Model model) {
        try {
            pacientService.validatePacient(pacient);
            pacientService.updatePacient(pacient);
            return "redirect:/pacienti";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pacient-form";
        }
    }

    //Afiseaza formularul de editare pentru un pacient specific
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Pacient pacient = pacientService.findById(id);
        model.addAttribute("pacient", pacient);
        return "pacient-form";
    }

    //Sterge un pacient si redirectioneaza la lista de pacienti
    @GetMapping("/delete/{id}")
    public String deletePacient(@PathVariable("id") int id) {
        pacientService.deletePacient(id);
        return "redirect:/pacienti";
    }

    //Afiseaza detalii medicale pentru un pacient specific
    @GetMapping("/detalii/{id}")
    public String detaliiPacient(@PathVariable int id, Model model) {
        List<Map<String, Object>> detalii = pacientDAO.obtineDetaliiMedicale(id);
        model.addAttribute("detalii", detalii);
        return "pacient-detalii";
    }

    //Afiseaza istoricul medical al unui pacient
    @GetMapping("/istoric/{id}")
    public String istoricPacient(@PathVariable int id, Model model) {
        List<Map<String, Object>> detalii = pacientDAO.obtineIstoricMedical(id);
        model.addAttribute("detalii", detalii);
        return "pacient-istoric";
    }
}
