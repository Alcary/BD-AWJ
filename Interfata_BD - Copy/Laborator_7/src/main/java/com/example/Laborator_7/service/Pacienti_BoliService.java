package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.Pacienti_BoliDAO;
import com.example.Laborator_7.entity.Pacienti_Boli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pacienti_BoliService {

    @Autowired
    private Pacienti_BoliDAO pacienti_boliDAO;

    public List<Pacienti_Boli> getAllPacientiBoli() {
        return pacienti_boliDAO.getAllPacientiBoli();
    }

    public int savePacientBoala(Pacienti_Boli pacienti_boli) {
        return pacienti_boliDAO.savePacientiBoli(pacienti_boli);
    }

}
