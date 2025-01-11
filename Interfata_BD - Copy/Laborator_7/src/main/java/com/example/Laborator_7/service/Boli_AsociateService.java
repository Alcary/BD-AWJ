/** Serviciu care gestioneaza logica aplicatiei pentru entitatea Boli_Asociate
 * @author Calaras Alexandru
 * @version 6 Ianuarie 2025
 */
package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.Boli_AsociateDAO;
import com.example.Laborator_7.entity.Boli_Asociate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Boli_AsociateService {

    @Autowired
    private Boli_AsociateDAO boli_asociateDAO;

    //Returneaza toate bolile asociate din baza de date
    public List<Boli_Asociate> getAllBoliAsociate() {
        return boli_asociateDAO.getAllBoli();
    }

    //Insereaza o noua boala asociata in baza de date
    public int insertBoalaAsociata(Boli_Asociate boliAsociate) {
        return boli_asociateDAO.insertBoalaAsociata(boliAsociate);
    }

    //Actualizeaza datele unei boli asociate existente in baza de date
    public int updateBoalaAsociata(Boli_Asociate boliAsociate) {
        return boli_asociateDAO.updateBoli(boliAsociate);
    }

    //Cauta o boala asociata dupa ID-ul unic
    public Boli_Asociate findById(int id) {
        return boli_asociateDAO.findById(id);
    }

    //Sterge o boala asociata din baza de date folosind ID-ul acesteia
    public void deleteBoalaAsociata(int id) {
        boli_asociateDAO.deleteBoli(id);
    }

    //Cauta bolile asociate dupa nume
    public List<Boli_Asociate> findByNume(String nume) {
        return boli_asociateDAO.findByNume(nume);
    }

    //Valideaza datele unei boli asociate inainte de a fi inserate sau actualizate
    public void validateBoala(Boli_Asociate boala) {
        //Valideaza numele bolii: trebuie sa contina doar litere si spatii
        if (!boala.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        //Valideaza tratamentul: campul trebuie sa contina un text valid
        if (!boala.getTratament().matches("^.*$")) {
            throw new IllegalArgumentException("Tratament invalid.");
        }
    }
}
