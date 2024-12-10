package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.Pacienti_MedicamenteDAO;
import com.example.Laborator_7.entity.Pacienti_Medicamente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pacienti_MedicamenteService {

    @Autowired
    private Pacienti_MedicamenteDAO pacientiMedicamenteDAO;

    public List<Pacienti_Medicamente> getAllPacientiMedicamente() {
        return pacientiMedicamenteDAO.getAllPacientiMedicamente();
    }

    public int savePacientMedicament(Pacienti_Medicamente pacientiMedicamente) {
        return pacientiMedicamenteDAO.savePacientMedicament(pacientiMedicamente);
    }

}
