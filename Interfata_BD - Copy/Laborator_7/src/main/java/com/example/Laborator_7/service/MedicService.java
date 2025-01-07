//Serviciu care gestioneaza logica aplicatiei pentru entitatea Medic
package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.MedicDAO;
import com.example.Laborator_7.entity.Medic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {

    @Autowired
    private MedicDAO medicDAO;

    //Returneaza toti medicii din baza de date
    public List<Medic> getAllMedics() {
        return medicDAO.getAllMedics();
    }

    //Cauta medici dupa nume
    public List<Medic> findByNume(String nume) {
        return medicDAO.findByNume(nume);
    }

    //Cauta un medic dupa ID-ul unic
    public Medic findById(int id) {
        return medicDAO.findById(id);
    }

    //Sterge un medic din baza de date folosind ID-ul acestuia
    public void deleteMedic(int id) {
        medicDAO.deleteMedic(id);
    }

    //Actualizeaza datele unui medic existent in baza de date
    public int updateMedic(Medic medic) {
        return medicDAO.updateMedic(medic);
    }

    //Insereaza un nou medic in baza de date
    public int insertMedic(Medic medic) {
        return medicDAO.insertMedic(medic);
    }

    //Cauta medici care lucreaza intr-un spital specific
    public List<Medic> findMedicBySpital(String spital) {
        return medicDAO.findMedicBySpital(spital);
    }

    //Valideaza datele unui medic inainte de a fi inserate sau actualizate
    public void validateMedic(Medic medic) {
        //Valideaza numele medicului: trebuie sa contina doar litere si spatii
        if (!medic.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        //Valideaza prenumele medicului: trebuie sa contina doar litere si spatii
        if (!medic.getPrenume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Prenume invalid: prenumele trebuie să conțină doar litere și spații.");
        }
        //Valideaza numarul de telefon: format international, intre 10 si 15 cifre
        if (!medic.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
        //Valideaza CNP-ul: trebuie sa aiba exact 13 cifre
        if (!medic.getCnp().matches("[0-9]{13}")) {
            throw new IllegalArgumentException("CNP invalid.");
        }
        //Valideaza sexul medicului: poate fi doar 'M' sau 'F'
        if (!medic.getSex().matches("[MF]")) {
            throw new IllegalArgumentException("Sex invalid: poate fi doar M sau F.");
        }
        //Valideaza orasul: trebuie sa contina doar litere si spatii
        if (!medic.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Valideaza judetul: trebuie sa contina doar litere si spatii
        if (!medic.getJudet().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Județ invalid: județul poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Verifica daca ID-ul spitalului este prezent
        if (medic.getIdSpital() == null) {
            throw new IllegalArgumentException("ID-ul spitalului invalid: trebuie să selectați un spital.");
        }

        //Verifica daca ID-ul spitalului exista in baza de date
        int spital = medicDAO.findLastIdSpital();
        if (medic.getIdSpital() > spital) {
            throw new IllegalArgumentException("Spitalul nu există. Ultimul ID din baza de date este " + spital);
        }
    }
}
