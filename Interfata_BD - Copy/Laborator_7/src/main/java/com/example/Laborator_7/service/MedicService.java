package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.MedicDAO;
import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.entity.Medic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class MedicService {

    @Autowired
    private MedicDAO medicDAO;

    public List<Medic> getAllMedics() {
        return medicDAO.getAllMedics();
    }

    public List<Medic> findByNume(String nume) {
        return medicDAO.findByNume(nume);
    }

    public Medic findById(int id) {
        return medicDAO.findById(id);
    }

    public void deleteMedic(int id) {
        medicDAO.deleteMedic(id);
    }

    public int updateMedic(Medic medic) {
        return medicDAO.updateMedic(medic);
    }

    public int insertMedic(Medic medic) {
        return medicDAO.insertMedic(medic);
    }

    public List<Medic> findMedicBySpital(String spital) {
        return medicDAO.findMedicBySpital(spital);
    }

    public void validateMedic(Medic medic) {
        if (!medic.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        if (!medic.getPrenume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Prenume invalid: prenumele trebuie să conțină doar litere și spații.");
        }
        if (!medic.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
        if (!medic.getCnp().matches("[0-9]{13}")) {
            throw new IllegalArgumentException("CNP invalid.");
        }
        if (!medic.getSex().matches("[MF]")) {
            throw new IllegalArgumentException("Poate fi doar M/F.");
        }
        if (!medic.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (!medic.getJudet().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Județ invalid: județul poate conține doar litere, cifre, spații și caractere românești.");
        }
    }
}
