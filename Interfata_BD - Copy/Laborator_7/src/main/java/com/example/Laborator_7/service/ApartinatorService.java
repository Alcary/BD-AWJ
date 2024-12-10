package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.ApartinatorDAO;
import com.example.Laborator_7.entity.Apartinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartinatorService {

    @Autowired
    private ApartinatorDAO apartinatorDAO;

    public List<Apartinator> getAllApartinatori() {
        return apartinatorDAO.getAllApartinatori();
    }

    public List<Apartinator> findByNume(String nume) {
        return apartinatorDAO.findByNume(nume);
    }

    public Apartinator findById(int id) {
        return apartinatorDAO.findById(id);
    }

    public void deleteApartinator(int id) {
        apartinatorDAO.deleteApartinator(id);
    }

    public int insertApartinator(Apartinator apartinator) {
        return apartinatorDAO.insertApartinator(apartinator);
    }

    public int updateApartinator(Apartinator apartinator) {
        return apartinatorDAO.updateApartinator(apartinator);
    }
}
