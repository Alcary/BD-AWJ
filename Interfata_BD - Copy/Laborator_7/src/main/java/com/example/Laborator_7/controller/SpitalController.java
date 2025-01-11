/** Este un controller care se ocupa de gestionarea cererilor legate de spitale
 * @author Calaras Alexandru
 * @version 5 Ianuarie 2025
 */
package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Spital;
import com.example.Laborator_7.service.SpitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/spitale")
public class SpitalController {

    @Autowired
    private SpitalService spitalService;

    //Returneaza toate spitalele si le afiseaza intr-o lista pe pagina web
    @GetMapping
    public String getAllSpitale(Model model) {
        List<Spital> spitale = spitalService.getAllHosptials();
        model.addAttribute("spitale", spitale);
        return "spitale";
    }

    //Cauta spitale dupa nume si afiseaza rezultatele
    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if (nume == null || nume.isEmpty()) {
            return "redirect:/spitale";
        }
        List<Spital> spitale = spitalService.findByNume(nume);
        model.addAttribute("spitale", spitale);
        return "spitale";
    }

    //Afiseaza formularul pentru adaugarea unui spital nou
    @GetMapping("/new")
    public String showNewForm(Model model) {
        Spital spital = new Spital();
        model.addAttribute("spital", spital);
        return "spital-form-insert";
    }

    //Proceseaza salvarea unui spital nou
    @PostMapping("/save")
    public String saveSpital(@ModelAttribute("spital") Spital spital, Model model) {
        try {
            spitalService.validateSpital(spital);
            spitalService.insertSpital(spital);
            return "redirect:/spitale";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "spital-form-insert";
        }
    }

    //Returneaza topul spitalelor pe baza unui criteriu specific
    @GetMapping("/topSpitale")
    public String getTopSpitale(Model model) {
        List<Spital> topSpitale = spitalService.findTopSpitale();
        model.addAttribute("spitale", topSpitale);
        return "spitale";
    }

    //Proceseaza actualizarea unui spital existent
    @PostMapping("/update")
    public String updateSpital(@ModelAttribute("spital") Spital spital, Model model) {
        try {
            spitalService.validateSpital(spital);
            spitalService.updateSpital(spital);
            return "redirect:/spitale";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "spital-form";
        }
    }

    //Afiseaza formularul de editare pentru un spital specific
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Spital spital = spitalService.findById(id);
        model.addAttribute("spital", spital);
        return "spital-form";
    }

    //Sterge un spital si redirectioneaza la lista de spitale
    @GetMapping("/delete/{id}")
    public String deleteSpital(@PathVariable("id") int id) {
        spitalService.deleteSpital(id);
        return "redirect:/spitale";
    }
}
