//Este un controller care se ocupa de gestionarea cererilor legate de companii farmaceutice
package com.example.Laborator_7.controller;

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

    //Returneaza toate companiile farmaceutice si le afiseaza intr-o lista pe pagina web
    @GetMapping
    public String getAllCompanii(Model model) {
        List<CompaniiFarmaceutice> companii = companiiFarmaceuticeService.getAllCompanii();
        model.addAttribute("companii", companii);
        return "companii_farmaceutice";
    }

    //Cauta companii farmaceutice dupa nume si afiseaza rezultatele
    @GetMapping("/search")
    public String findByNume(@RequestParam("nume") String nume, Model model) {
        if(nume == null || nume.isEmpty()) {
            return "redirect:/companii_farmaceutice";
        }
        List<CompaniiFarmaceutice> companii = companiiFarmaceuticeService.findByNume(nume);
        model.addAttribute("companii", companii);
        return "companii_farmaceutice";
    }

    //Afiseaza formularul pentru adaugarea unei noi companii farmaceutice
    @GetMapping("/new")
    public String showNewForm(Model model) {
        CompaniiFarmaceutice companie = new CompaniiFarmaceutice();
        model.addAttribute("companie", companie);
        return "companie-form-insert";
    }

    //Proceseaza salvarea unei companii farmaceutice noi sau actualizate
    @PostMapping("/save")
    public String saveCompanie(@ModelAttribute("companie") CompaniiFarmaceutice companie, Model model) {
        try{
            companiiFarmaceuticeService.validateCompanie(companie);
            companiiFarmaceuticeService.insertCompanie(companie);
            return "redirect:/companii_farmaceutice";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "companie-form-insert";
        }
    }

    //Proceseaza actualizarea unei companii farmaceutice
    @PostMapping("/update")
    public String updateCompanie(@ModelAttribute("companie") CompaniiFarmaceutice companie, Model model) {
        try{
            companiiFarmaceuticeService.validateCompanie(companie);
            companiiFarmaceuticeService.updateCompanie(companie);
            return "redirect:/companii_farmaceutice";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "companie-form";
        }
    }

    //Afiseaza formularul de editare pentru o companie farmaceutica specifica
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        CompaniiFarmaceutice companie = companiiFarmaceuticeService.findById(id);
        model.addAttribute("companie", companie);
        return "companie-form";
    }

    //Sterge o companie farmaceutica si redirectioneaza la lista de companii
    @GetMapping("/delete/{id}")
    public String deleteCompanie(@PathVariable("id") int id) {
        companiiFarmaceuticeService.deleteCompanie(id);
        return "redirect:/companii_farmaceutice";
    }
}
