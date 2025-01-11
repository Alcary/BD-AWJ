/**Este un controller care se ocupa de gestionarea cererilor legate de boli asociate
 * @author Calaras Alexandru
 * @version 27 Decembrie 2024
 */
package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Boli_Asociate;
import com.example.Laborator_7.service.Boli_AsociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boli_asociate")
public class Boli_AsociateController {

    @Autowired
    private Boli_AsociateService boli_AsociateService;

    //Returneaza toate bolile si ii afiseaza intr-o lista pe pagina web
    @GetMapping
    public String getAllBoli(Model model) {
        List<Boli_Asociate> boliAsociate = boli_AsociateService.getAllBoliAsociate();
        model.addAttribute("boliAsociate", boliAsociate);
        //returneaza numele view-ului
        return "boli_asociate";
    }

    //afiseaza formularul pentru adaugarea unei noi boli
    @GetMapping("/new")
    public String showNewFormBoliAsociate(Model model) {
        Boli_Asociate boli = new Boli_Asociate();
        model.addAttribute("boli", boli);
        return "boli_asociate-form-insert";
    }

    //salveaza o boala noua in baza de date
    @PostMapping("/save")
    public String saveBoalaAsociata(@ModelAttribute("boala") Boli_Asociate boala, Model model) {
        try{
            boli_AsociateService.validateBoala(boala);
            boli_AsociateService.insertBoalaAsociata(boala);
            return "redirect:/boli_asociate";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "boli_asociate-form-insert";
        }
    }

    //actualizeaza o boala existenta
    @PostMapping("/update")
    public String updateBoalaAsociata(@ModelAttribute("boala") Boli_Asociate boala, Model model) {
        try{
            boli_AsociateService.validateBoala(boala);
            boli_AsociateService.updateBoalaAsociata(boala);
            return "redirect:/boli_asociate";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "boli_asociate-form";
        }

    }

    //afiseaza formularul pentru editarea unei boli
    @GetMapping("/edit/{id}")
    public String showEditFormBoliAsociate(@PathVariable("id") int id, Model model) {
        Boli_Asociate boala = boli_AsociateService.findById(id);
        model.addAttribute("boala", boala);
        return "/boli_asociate-form";
    }

    //sterge o boala din baza de date
    @GetMapping("/delete/{id}")
    public String deleteBoalaAsociata(@PathVariable("id") int id) {
        boli_AsociateService.deleteBoalaAsociata(id);
        return "redirect:/boli_asociate";
    }

    //cauta boli dupa nume
    @GetMapping("/search")
    public String findByNumeBoalaAsociata(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/boli_asociate";
        }
        List<Boli_Asociate> boliAsociate = boli_AsociateService.findByNume(nume);
        model.addAttribute("boliAsociate", boliAsociate);
        return "boli_asociate";
    }
}
