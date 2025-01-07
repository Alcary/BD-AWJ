//Serviciu care gestioneaza logica aplicatiei pentru entitatea Apartinator
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

    //Returneaza lista tuturor apartinatorilor din baza de date
    public List<Apartinator> getAllApartinatori() {
        return apartinatorDAO.getAllApartinatori();
    }

    //Cauta apartinatori dupa nume (partial sau complet)
    public List<Apartinator> findByNume(String nume) {
        return apartinatorDAO.findByNume(nume);
    }

    //Cauta un apartinator dupa ID-ul unic
    public Apartinator findById(int id) {
        return apartinatorDAO.findById(id);
    }

    //Sterge un apartinator din baza de date folosind ID-ul acestuia
    public void deleteApartinator(int id) {
        apartinatorDAO.deleteApartinator(id);
    }

    //Insereaza un nou apartinator in baza de date
    public int insertApartinator(Apartinator apartinator) {
        return apartinatorDAO.insertApartinator(apartinator);
    }

    //Actualizeaza datele unui apartinator existent in baza de date
    public int updateApartinator(Apartinator apartinator) {
        return apartinatorDAO.updateApartinator(apartinator);
    }

    //Valideaza datele unui apartinator inainte de a fi inserat sau actualizat
    public void validateApartinator(Apartinator apartinator) {
        //Valideaza numele: trebuie să conțină doar litere, spații, puncte și linii
        if (!apartinator.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        //Valideaza prenumele: aceleași reguli ca pentru nume
        if (!apartinator.getPrenume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Prenume invalid: prenumele trebuie să conțină doar litere și spații.");
        }
        //Valideaza numărul de telefon: format internațional (10-15 cifre, opțional '+')
        if (!apartinator.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
    }
}
