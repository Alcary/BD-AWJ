package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.MedicamenteDAO;
import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.entity.Medicamente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamenteService {

    @Autowired
    private MedicamenteDAO medicamenteDAO;

    public List<Medicamente> getAllMedicamente() {
        return medicamenteDAO.getAllMedicamente();
    }

    public List<Medicamente> findByNume(String nume) {
        return medicamenteDAO.findByNume(nume);
    }

    public int insertMedicament(Medicamente medicament) {
        return medicamenteDAO.insertMedicament(medicament);
    }

    public int updateMedicament(Medicamente medicament) {
        return medicamenteDAO.updateMedicament(medicament);
    }

    public Medicamente findById(int id) {
        return medicamenteDAO.findById(id);
    }

    public void deleteMedicament(int id) {
        medicamenteDAO.deleteMedicament(id);
    }
}
