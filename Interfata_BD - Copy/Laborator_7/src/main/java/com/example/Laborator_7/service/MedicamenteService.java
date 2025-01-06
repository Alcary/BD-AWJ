package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.MedicamenteDAO;
import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.entity.Medic;
import com.example.Laborator_7.entity.Medicamente;
import com.example.Laborator_7.entity.Pacient;
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

    public List<Medicamente> findMedicamentByCompanieOras(String oras) {
        return medicamenteDAO.findMedicamentByCompanieOras(oras);
    }

    public void deleteMedicament(int id) {
        medicamenteDAO.deleteMedicament(id);
    }

    public void validateMedicament(Medicamente medicamente) {
        if (!medicamente.getNumeMedicament().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        if (medicamente.getDurataTratament() < 0) {
            throw new IllegalArgumentException("Durată invalidă: durata tratamentului trebuie să fie un număr pozitiv.");
        }
        if (medicamente.getIdCompanie() == null){
            throw new IllegalArgumentException("ID-ul companiei invalid: trebuie să selectați o companie.");
        }
    }
}
