package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.MedicDAO;
import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.entity.Medic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
