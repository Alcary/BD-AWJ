package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.ApartinatorDAO;
import com.example.Laborator_7.entity.Apartinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


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

    public void validateApartinator(Apartinator apartinator) {
        if (!apartinator.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        if (!apartinator.getPrenume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")){
            throw new IllegalArgumentException("Prenume invalid: prenumele trebuie să conțină doar litere și spații.");
        }
        if (!apartinator.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
    }
}
