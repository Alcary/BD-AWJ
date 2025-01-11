/**Este un controller care se ocupa de gestionarea cererilor legate de apartinatori
 * @author Calaras Alexandru
 * @version 8 Ianuarie 2025
 */
package com.example.Laborator_7.controller;

import com.example.Laborator_7.entity.Apartinator;
import com.example.Laborator_7.service.ApartinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/apartinatori")
public class ApartinatorController {

    @Autowired
    private ApartinatorService apartinatorService;

    //Returneaza toti apartinatorii si ii afiseaza intr-o lista pe pagina web
    @GetMapping
    public String getAllApartinatori(Model model) {
        List<Apartinator> apartinatori = apartinatorService.getAllApartinatori();
        model.addAttribute("apartinatori", apartinatori);
        return "apartinatori";
    }

    //Afiseaza formularul pentru adaugarea unui nou apartinator
    @GetMapping("/new")
    public String showNewForm(Model model) {
        Apartinator apartinator = new Apartinator();
        model.addAttribute("apartinator", apartinator);
        return "apartinator-form-insert";
    }

    //Proceseaza salvarea unui apartinator nou sau actualizat
    @PostMapping("/save")
    public String saveApartinator(@ModelAttribute("apartinator") Apartinator apartinator, Model model) {
        try{
            apartinatorService.validateApartinator(apartinator);
            apartinatorService.insertApartinator(apartinator);
            return "redirect:/apartinatori";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "apartinator-form-insert";
        }
    }

    //Proceseaza actualizarea unui apartinator
    @PostMapping("/update")
    public String updateApartinator(@ModelAttribute("apartinator") Apartinator apartinator, Model model) {
        try{
            apartinatorService.validateApartinator(apartinator);
            apartinatorService.updateApartinator(apartinator);
            return "redirect:/apartinatori";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "apartinator-form";
        }
    }

    //Afiseaza formularul de editare pentru un apartinator specific
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Apartinator apartinator = apartinatorService.findById(id);
        model.addAttribute("apartinator", apartinator);
        return "apartinator-form";
    }

    //Sterge un apartinator si redirectioneaza la lista de apartinatori
    @GetMapping("/delete/{id}")
    public String deleteApartinator(@PathVariable("id") int id) {
        apartinatorService.deleteApartinator(id);
        return "redirect:/apartinatori";
    }

    //Cauta apartinatori dupa nume si afiseaza rezultatele
    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/apartinatori";
        }
        List<Apartinator> apartinatori = apartinatorService.findByNume(nume);
        model.addAttribute("apartinatori", apartinatori);
        return "apartinatori";
    }
}
