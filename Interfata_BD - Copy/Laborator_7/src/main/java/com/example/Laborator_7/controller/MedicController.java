/**Este un controller care se ocupa de gestionarea cererilor legate de Medic
 * @author Calaras Alexandru
 * @version 8 Ianuarie 2025
 */
package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Medic;
import org.springframework.stereotype.Controller;
import com.example.Laborator_7.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medici")
public class MedicController {

    @Autowired
    private MedicService medicService;

    //Returneaza toti medicii si ii afiseaza intr-o lista pe pagina web
    @GetMapping
    public String getAllMedics(Model model) {
        List<Medic> medici = medicService.getAllMedics();
        model.addAttribute("medici", medici);
        return "medici";
    }

    //Cauta medici dupa nume si afiseaza rezultatele
    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/medici";
        }
        List<Medic> medici = medicService.findByNume(nume);
        model.addAttribute("medici", medici);
        return "medici";
    }

    //Cauta medici dupa spital si afiseaza rezultatele
    @GetMapping("/searchSpital")
    public String getMedicSpital(@RequestParam("spital") String spital, Model model) {
        List<Medic> medici = medicService.findMedicBySpital(spital);
        model.addAttribute("medici", medici);
        return "medici";
    }

    //Afiseaza formularul pentru adaugarea unui medic nou
    @GetMapping("/new")
    public String showNewForm(Model model) {
        Medic medic = new Medic();
        model.addAttribute("medic", medic);
        return "medic-form-insert";
    }

    //Proceseaza salvarea unui medic nou sau actualizat
    @PostMapping("/save")
    public String saveMedic(@ModelAttribute("medic") Medic medic, Model model) {
        try {
            //Seteaza ID-ul supervizorului ca null daca este 0
            if (medic.getIdSupervizor() != null && medic.getIdSupervizor() == 0) {
                medic.setIdSupervizor(null);
            }
            medicService.validateMedic(medic);
            medicService.insertMedic(medic);
            return "redirect:/medici";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "medic-form-insert";
        }
    }

    //Proceseaza actualizarea unui medic
    @PostMapping("/update")
    public String updateMedic(@ModelAttribute("medic") Medic medic, Model model) {
        try {
            //Seteaza ID-ul supervizorului ca null daca este 0
            if (medic.getIdSupervizor() != null && medic.getIdSupervizor() == 0) {
                medic.setIdSupervizor(null);
            }
            medicService.validateMedic(medic);
            medicService.updateMedic(medic);
            return "redirect:/medici";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "medic-form";
        }
    }

    //Afiseaza formularul de editare pentru un medic specific
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Medic medic = medicService.findById(id);
        model.addAttribute("medic", medic);
        return "medic-form";
    }

    //Sterge un medic si redirectioneaza la lista de medici
    @GetMapping("/delete/{id}")
    public String deleteMedic(@PathVariable("id") int id) {
        medicService.deleteMedic(id);
        return "redirect:/medici";
    }
}
