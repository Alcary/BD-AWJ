package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.Boli_AsociateDAO;
import com.example.Laborator_7.entity.Boli_Asociate;
import com.example.Laborator_7.entity.CompaniiFarmaceutice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Boli_AsociateService {

    @Autowired
    private Boli_AsociateDAO boli_asociateDAO;

    public List<Boli_Asociate> getAllBoliAsociate() {
        return boli_asociateDAO.getAllBoli();
    }

    public int insertBoalaAsociata(Boli_Asociate boliAsociate) {
        return boli_asociateDAO.insertBoalaAsociata(boliAsociate);
    }

    public int updateBoalaAsociata(Boli_Asociate boliAsociate) {
        return boli_asociateDAO.updateBoli(boliAsociate);
    }

    public Boli_Asociate findById(int id) {
        return boli_asociateDAO.findById(id);
    }

    public void deleteBoalaAsociata(int id) {
        boli_asociateDAO.deleteBoli(id);
    }

    public List<Boli_Asociate> findByNume(String nume) {
        return boli_asociateDAO.findByNume(nume);
    }

    public void validateBoala(Boli_Asociate boala) {
        if (!boala.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        if (!boala.getTratament().matches("^.*$")) {
            throw new IllegalArgumentException("Tratament invalid.");
        }
    }
}
