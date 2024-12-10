//package com.example.Laborator_7.controller;
//
//import com.example.Laborator_7.entity.Apartinator;
//import com.example.Laborator_7.entity.Boli_Asociate;
//import com.example.Laborator_7.service.ApartinatorService;
//import com.example.Laborator_7.service.Boli_AsociateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private ApartinatorService apartinatorService;
//    @Autowired
//    private Boli_AsociateService boli_AsociateService;
//
//
////    Apartinatori
//
//    @GetMapping("/apartinatori")
//    public String getApartinatoriAdminView(Model model) {
//        List<Apartinator> apartinatori = apartinatorService.getAllApartinatori();
//        model.addAttribute("apartinatori", apartinatori);
//        return "admin/apartinatori";
//    }
//
//    @GetMapping("/apartinatori/new")
//    public String showNewForm(Model model) {
//        Apartinator apartinator = new Apartinator();
//        model.addAttribute("apartinator", apartinator);
//        return "admin/apartinator-form-insert";
//    }
//
//    @PostMapping("/apartinatori/save")
//    public String saveApartinator(@ModelAttribute("apartinator") Apartinator apartinator) {
//        apartinatorService.insertApartinator(apartinator);
//        return "redirect:/admin/apartinatori";
//    }
//
//    @PostMapping("/apartinatori/update")
//    public String updateApartinator(@ModelAttribute("apartinator") Apartinator apartinator) {
//        apartinatorService.updateApartinator(apartinator);
//        return "redirect:/admin/apartinatori";
//    }
//
//    @GetMapping("/apartinatori/edit/{id}")
//    public String showEditForm(@PathVariable("id") int id, Model model) {
//        Apartinator apartinator = apartinatorService.findById(id);
//        model.addAttribute("apartinator", apartinator);
//        return "admin/apartinator-form";
//    }
//
//    @GetMapping("/apartinatori/delete/{id}")
//    public String deleteApartinator(@PathVariable("id") int id) {
//        apartinatorService.deleteApartinator(id);
//        return "redirect:/admin/apartinatori";
//    }
//
//    @GetMapping("/apartinatori/search")
//    public String findByNume(@RequestParam("nume") String nume, Model model) {
//        if(nume == null || nume.isEmpty()) {
//            return "redirect:/admin/apartinatori";
//        }
//        List<Apartinator> apartinatori = apartinatorService.findByNume(nume);
//        model.addAttribute("apartinatori", apartinatori);
//        return "apartinatori";
//    }
//
////    Boli_Asociate
//
//    @GetMapping("/boli_asociate")
//    public String getBoliAsociateAdminView(Model model) {
//        List<Boli_Asociate> boli = boli_AsociateService.getAllBoliAsociate();
//        model.addAttribute("boli", boli);
//        return "admin/boli_asociate";
//    }
//
//    @GetMapping("/boli_asociate/new")
//    public String showNewFormBoliAsociate(Model model) {
//        Boli_Asociate boli = new Boli_Asociate();
//        model.addAttribute("boli", boli);
//        return "admin/boli_asociate-form-insert";
//    }
//
//    @PostMapping("/boli_asociate/save")
//    public String saveBoalaAsociata(@ModelAttribute("boala") Boli_Asociate boala) {
//        boli_AsociateService.insertBoalaAsociata(boala);
//        return "redirect:/admin/boli_asociate";
//    }
//
//    @PostMapping("/boli_asociate/update")
//    public String updateBoalaAsociata(@ModelAttribute("boala") Boli_Asociate boala) {
//        boli_AsociateService.updateBoalaAsociata(boala);
//        return "redirect:/admin/boli_asociate";
//    }
//
//    @GetMapping("/boli_asociate/edit/{id}")
//    public String showEditFormBoliAsociate(@PathVariable("id") int id, Model model) {
//        Boli_Asociate boala = boli_AsociateService.findById(id);
//        model.addAttribute("boala", boala);
//        return "admin/boli_asociate-form";
//    }
//
//    @GetMapping("/boli_asociate/delete/{id}")
//    public String deleteBoalaAsociata(@PathVariable("id") int id) {
//        boli_AsociateService.deleteBoalaAsociata(id);
//        return "redirect:/admin/boli_asociate";
//    }
//
//    @GetMapping("/boli_asociate/search")
//    public String findByNumeBoalaAsociata(@RequestParam("nume") String nume, Model model) {
//        if(nume == null || nume.isEmpty()) {
//            return "redirect:/admin/boli_asociate";
//        }
//        List<Boli_Asociate> boliAsociate = boli_AsociateService.findByNume(nume);
//        model.addAttribute("boli_asociate", boliAsociate);
//        return "boli_asociate";
//    }
//}
