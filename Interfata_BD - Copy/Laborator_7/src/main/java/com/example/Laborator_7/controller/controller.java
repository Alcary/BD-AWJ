package com.example.Laborator_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    //Gestioneaza cererea GET pentru pagina principala
    @GetMapping("/")
    public String index() {
        //Returneaza pagina principala
        return "index";
    }
}
