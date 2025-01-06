package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.CompaniiFarmaceuticeDAO;
import com.example.Laborator_7.entity.CompaniiFarmaceutice;
import com.example.Laborator_7.entity.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniiFarmaceuticeService {

    @Autowired
    private CompaniiFarmaceuticeDAO companiiFarmaceuticeDAO;

    public List<CompaniiFarmaceutice> getAllCompanii() {
        return companiiFarmaceuticeDAO.getAllCompanii();
    }

    public List<CompaniiFarmaceutice> findByNume(String nume) {
        return companiiFarmaceuticeDAO.findByNume(nume);
    }

    public CompaniiFarmaceutice findById(int id) {
        return companiiFarmaceuticeDAO.findById(id);
    }

    public void updateCompanie(CompaniiFarmaceutice companiiFarmaceutice) {
        companiiFarmaceuticeDAO.updateCompanie(companiiFarmaceutice);
    }

    public int insertCompanie(CompaniiFarmaceutice companiiFarmaceutice) {
        return companiiFarmaceuticeDAO.insertCompanie(companiiFarmaceutice);
    }

    public void deleteCompanie(int id) {
        companiiFarmaceuticeDAO.deleteCompanie(id);
    }

    public void validateCompanie(CompaniiFarmaceutice companie) {
        if (!companie.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        if (!companie.getStrada().matches("^[a-zA-Z0-9\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Stradă invalidă: strada poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (!companie.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        if (!companie.getTara().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Țară invaliă: țara poate conține doar litere, cifre, spații și caractere românești.");
        }
    }
}
