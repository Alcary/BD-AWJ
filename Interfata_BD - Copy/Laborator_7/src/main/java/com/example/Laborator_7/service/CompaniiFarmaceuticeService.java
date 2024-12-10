package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.CompaniiFarmaceuticeDAO;
import com.example.Laborator_7.entity.Admin;
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

    public int saveCompanie(CompaniiFarmaceutice companiiFarmaceutice) {
        return companiiFarmaceuticeDAO.saveCompanie(companiiFarmaceutice);
    }
}
