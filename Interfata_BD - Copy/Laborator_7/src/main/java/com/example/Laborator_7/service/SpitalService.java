package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.SpitalDAO;
import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.entity.Spital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteSpital(int id) {
        spitalDAO.deleteSpital(id);
    }
}
