package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.CompaniiFarmaceuticeDAO;
import com.example.Laborator_7.entity.CompaniiFarmaceutice;
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
}
