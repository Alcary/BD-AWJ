/** Servciu care gestioneaza logica aplicatiei pentru entitatea CompaniiFarmaceutice
 * @author Calaras Alexandru
 * @version 6 Ianuarie 2025
 */
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

    //Returneaza toate companiile farmaceutice din baza de date
    public List<CompaniiFarmaceutice> getAllCompanii() {
        return companiiFarmaceuticeDAO.getAllCompanii();
    }

    //Cauta companii farmaceutice dupa nume
    public List<CompaniiFarmaceutice> findByNume(String nume) {
        return companiiFarmaceuticeDAO.findByNume(nume);
    }

    //Gaseste o companie farmaceutica dupa ID-ul unic
    public CompaniiFarmaceutice findById(int id) {
        return companiiFarmaceuticeDAO.findById(id);
    }

    //Actualizeaza datele unei companii farmaceutice existente
    public void updateCompanie(CompaniiFarmaceutice companiiFarmaceutice) {
        companiiFarmaceuticeDAO.updateCompanie(companiiFarmaceutice);
    }

    //Insereaza o noua companie farmaceutica in baza de date
    public int insertCompanie(CompaniiFarmaceutice companiiFarmaceutice) {
        return companiiFarmaceuticeDAO.insertCompanie(companiiFarmaceutice);
    }

    //Sterge o companie farmaceutica din baza de date folosind ID-ul acesteia
    public void deleteCompanie(int id) {
        companiiFarmaceuticeDAO.deleteCompanie(id);
    }

    //Valideaza datele unei companii farmaceutice inainte de a fi inserate sau actualizate
    public void validateCompanie(CompaniiFarmaceutice companie) {
        //Valideaza numele companiei: trebuie sa contina doar litere si spatii
        if (!companie.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        //Valideaza numele strazii: poate contine litere, cifre, spatii si caractere speciale
        if (!companie.getStrada().matches("^[a-zA-Z0-9\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Stradă invalidă: strada poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Valideaza orasul: trebuie sa contina doar litere si spatii
        if (!companie.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Valideaza tara: trebuie sa contina doar litere si spatii
        if (!companie.getTara().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Țară invaliă: țara poate conține doar litere, cifre, spații și caractere românești.");
        }
    }
}
