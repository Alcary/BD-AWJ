/** Este un controller care se ocupa de gestionarea cererilor legate de Medicamente
 * @author Calaras Alexandru
 * @version 6 Ianuarie 2025
 */
package com.example.Laborator_7.controller;

import com.example.Laborator_7.dao.MedicamenteDAO;
import com.example.Laborator_7.entity.Medicamente;
import com.example.Laborator_7.service.MedicamenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicamente")
public class MedicamenteController {

    @Autowired
    private MedicamenteService medicamenteService;
    @Autowired
    private MedicamenteDAO medicamenteDAO;

    //Returneaza toate medicamentele si le afiseaza intr-o lista pe pagina web
    @GetMapping
    public String getAllMedicamente(Model model) {
        List<Medicamente> medicamentes = medicamenteService.getAllMedicamente();
        model.addAttribute("medicamente", medicamentes);
        return "medicamente";
    }

    //Cauta medicamente dupa nume si afiseaza rezultatele
    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/medicamente";
        }
        List<Medicamente> medicamente = medicamenteService.findByNume(nume);
        model.addAttribute("medicamente", medicamente);
        return "medicamente";
    }

    //Cauta medicamente dupa orasul companiei si afiseaza rezultatele
    @GetMapping("/searchCompanieOras")
    public String getMedicamenteOras(@RequestParam("oras") String oras, Model model) {
        List<Medicamente> medicamente = medicamenteService.findMedicamentByCompanieOras(oras);
        model.addAttribute("medicamente", medicamente);
        return "medicamente";
    }

    //Afiseaza formularul pentru adaugarea unui nou medicament
    @GetMapping("/new")
    public String showNewForm(Model model) {
        Medicamente medicamente = new Medicamente();
        model.addAttribute("medicamente", medicamente);
        return "medicamente-form-insert";
    }

    //Proceseaza salvarea unui medicament nou sau actualizat
    @PostMapping("/save")
    public String saveMedicament(@ModelAttribute("medicamente") Medicamente medicamente, Model model) {
        try{
            medicamenteService.validateMedicament(medicamente);
            medicamenteService.insertMedicament(medicamente);
            return "redirect:/medicamente";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "medicamente-form-insert";
        }
    }

    //Proceseaza actualizarea unui medicament
    @PostMapping("/update")
    public String updateMedicament(@ModelAttribute("medicamente") Medicamente medicamente, Model model) {
        try{
            medicamenteService.validateMedicament(medicamente);
            medicamenteService.updateMedicament(medicamente);
            return "redirect:/medicamente";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "medicamente-form";
        }
    }

    //Afiseaza formularul de editare pentru un medicament specific
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Medicamente medicamente = medicamenteService.findById(id);
        model.addAttribute("medicamente", medicamente);
        return "medicamente-form";
    }

    //Sterge un medicament si redirectioneaza la lista de medicamente
    @GetMapping("/delete/{id}")
    public String deleteMedicament(@PathVariable("id") int id) {
        medicamenteService.deleteMedicament(id);
        return "redirect:/medicamente";
    }
}
