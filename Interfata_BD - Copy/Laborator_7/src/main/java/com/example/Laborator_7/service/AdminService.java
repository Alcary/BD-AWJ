//Serviciu care gestioneaza logica aplicatiei pentru entitatea Admin
package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.AdminDAO;
import com.example.Laborator_7.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    //Cauta un administrator in baza de date dupa username
    public Admin findByUsername(String username) {
        return adminDAO.findByUsername(username);
    }
}
