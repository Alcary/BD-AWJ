package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.SpitalDAO;
import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.entity.Pacient;
import com.example.Laborator_7.entity.Spital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class SpitalService {

    @Autowired
    private SpitalDAO spitalDAO;

    public List<Spital> getAllHosptials() {
        return spitalDAO.getAllHospitals();
    }

    public List<Spital> findByNume(String nume) {
        return spitalDAO.findByNume(nume);
    }

    public int insertSpital(Spital spital) {
        return spitalDAO.insertSpital(spital);
    }

    public int updateSpital(Spital spital) {
        return spitalDAO.updateSpital(spital);
    }

    public Spital findById(int id) {
        return spitalDAO.findById(id);
    }

    public List<Spital> findTopSpitale() {
        return spitalDAO.findTopSpitale();
    }

    public void deleteSpital(int id) {
        spitalDAO.deleteSpital(id);
    }

    public void validateSpital(Spital spital) {
        if (!spital.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și caractere românești.");
        }
        if (!spital.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
        if (!spital.getStrada().matches("^^[a-zA-Z0-9\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Stradă invalidă: strada poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (!spital.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (!spital.getJudet().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Județ invalid: județul poate conține doar litere, cifre, spații și caractere românești.");
        }
    }
}
