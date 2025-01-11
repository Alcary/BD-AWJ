/** Serviciu care gestioneaza logica aplicatiei pentru entitatea Spital
 * @author Calaras Alexandru
 * @version 6 Ianuarie 2025
 */
package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.SpitalDAO;
import com.example.Laborator_7.entity.Spital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpitalService {

    @Autowired
    private SpitalDAO spitalDAO;

    //Returneaza toate spitalele din baza de date
    public List<Spital> getAllHosptials() {
        return spitalDAO.getAllHospitals();
    }

    //Cauta spitale dupa nume
    public List<Spital> findByNume(String nume) {
        return spitalDAO.findByNume(nume);
    }

    //Insereaza un nou spital in baza de date
    public int insertSpital(Spital spital) {
        return spitalDAO.insertSpital(spital);
    }

    //Actualizeaza datele unui spital existent
    public int updateSpital(Spital spital) {
        return spitalDAO.updateSpital(spital);
    }

    //Cauta un spital dupa ID-ul unic
    public Spital findById(int id) {
        return spitalDAO.findById(id);
    }

    //Returneaza primele spitale in functie de numarul de medici asociati
    public List<Spital> findTopSpitale() {
        return spitalDAO.findTopSpitale();
    }

    //Sterge un spital din baza de date folosind ID-ul acestuia
    public void deleteSpital(int id) {
        spitalDAO.deleteSpital(id);
    }

    //Valideaza datele unui spital inainte de a fi inserate sau actualizate
    public void validateSpital(Spital spital) {
        //Valideaza numele spitalului: trebuie sa contina doar litere si caractere românești
        if (!spital.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și caractere românești.");
        }
        //Valideaza numarul de telefon: format international, intre 10 si 15 cifre
        if (!spital.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
        //Valideaza strada: poate contine litere, cifre, spatii si caractere speciale
        if (!spital.getStrada().matches("^[a-zA-Z0-9\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Stradă invalidă: strada poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Valideaza orasul: trebuie sa contina doar litere si caractere românești
        if (!spital.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Valideaza judetul: trebuie sa contina doar litere si caractere românești
        if (!spital.getJudet().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Județ invalid: județul poate conține doar litere, cifre, spații și caractere românești.");
        }
    }
}

