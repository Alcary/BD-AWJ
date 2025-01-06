package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.PacientDAO;
import com.example.Laborator_7.entity.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;


import java.util.List;

@Service
public class PacientService {

    @Autowired
    private PacientDAO pacientDAO;

    public List<Pacient> getAllPatients() {
        return pacientDAO.getAllPatients();
    }

    public List<Pacient> findByNume(String nume) {
        return pacientDAO.findByNume(nume);
    }

    public void deletePacient(int id) {
        pacientDAO.deletePacient(id);
    }

    public int insertPacient(Pacient pacient) {
        return pacientDAO.insertPacient(pacient);
    }

    public int updatePacient(Pacient pacient) {
        return pacientDAO.updatePacient(pacient);
    }

    public Pacient findById(int id) {
        return pacientDAO.findById(id);
    }

    public List<Pacient>findPacientBoala(String boala){
        return pacientDAO.findPacientBoala(boala);
    }

    public void validatePacient(Pacient pacient) {
        if (!pacient.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        if (!pacient.getPrenume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Prenume invalid: prenumele trebuie să conțină doar litere și spații.");
        }
        if (!pacient.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
        if (!pacient.getCnp().matches("[0-9]{13}")) {
            throw new IllegalArgumentException("CNP invalid.");
        }
        if (!pacient.getSex().matches("[MF]")) {
            throw new IllegalArgumentException("Poate fi doar M/F.");
        }
        if (!pacient.getStrada().matches("^[a-zA-Z0-9\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Stradă invalidă: strada poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (!pacient.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (!pacient.getJudet().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Județ invalid: județul poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (pacient.getDataNasterii() == null) {
            throw new IllegalArgumentException("Data Nașterii este obligatorie.");
        }
        if (pacient.getDataInrolareProgram() == null) {
            throw new IllegalArgumentException("Data Înrolării în Program este obligatorie.");
        }
        if (pacient.getId_medic() == null){
            throw new IllegalArgumentException("ID-ul medicului invalid: trebuie să selectați un medic.");
        }
    }
}