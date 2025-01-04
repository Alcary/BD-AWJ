package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.PatientDAO;
import com.example.Laborator_7.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public List<Patient> findByNume(String nume) {
        return patientDAO.findByNume(nume);
    }

    public void deletePacient(int id) {
        patientDAO.deletePacient(id);
    }

    public int insertPacient(Patient pacient) {
        return patientDAO.insertPacient(pacient);
    }

    public int updatePacient(Patient pacient) {
        return patientDAO.updatePacient(pacient);
    }

    public Patient findById(int id) {
        return patientDAO.findById(id);
    }

    public void insertPacientCuDetalii(Patient pacient, List<Integer> boli, List<Integer> medicamente, java.sql.Date dataaparitie, java.sql.Date datastarttratament) {
        int id_pacient = patientDAO.insertPacient(pacient);

        if(medicamente != null && !medicamente.isEmpty()) {
            for(Integer medicamentId : medicamente) {
                patientDAO.addMedicamentForPacient(id_pacient, medicamentId, datastarttratament);
            }
        }

        if(boli != null && !boli.isEmpty()) {
            for(Integer boalaId : boli) {
                patientDAO.addBoalaForPacient(id_pacient, boalaId, dataaparitie);
            }
        }
    }

}