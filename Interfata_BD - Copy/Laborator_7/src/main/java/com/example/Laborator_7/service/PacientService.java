/** Serviciu care gestioneaza logica aplicatiei pentru entitatea Pacient
 * @author Calaras Alexandru
 * @version 6 Ianuarie 2025
 */
package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.PacientDAO;
import com.example.Laborator_7.entity.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientService {

    @Autowired
    private PacientDAO pacientDAO;

    //Returneaza toti pacientii din baza de date
    public List<Pacient> getAllPatients() {
        return pacientDAO.getAllPatients();
    }

    //Cauta pacienti dupa nume
    public List<Pacient> findByNume(String nume) {
        return pacientDAO.findByNume(nume);
    }

    //Sterge un pacient din baza de date folosind ID-ul acestuia
    public void deletePacient(int id) {
        pacientDAO.deletePacient(id);
    }

    //Insereaza un nou pacient in baza de date
    public int insertPacient(Pacient pacient) {
        return pacientDAO.insertPacient(pacient);
    }

    //Actualizeaza datele unui pacient existent in baza de date
    public int updatePacient(Pacient pacient) {
        return pacientDAO.updatePacient(pacient);
    }

    //Cauta un pacient dupa ID-ul unic
    public Pacient findById(int id) {
        return pacientDAO.findById(id);
    }

    //Cauta pacienti care au o anumita boala
    public List<Pacient> findPacientBoala(String boala) {
        return pacientDAO.findPacientBoala(boala);
    }

    //Valideaza datele unui pacient inainte de a fi inserate sau actualizate
    public void validatePacient(Pacient pacient) {
        //Valideaza numele pacientului: trebuie sa contina doar litere si spatii
        if (!pacient.getNume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        //Valideaza prenumele pacientului: trebuie sa contina doar litere si spatii
        if (!pacient.getPrenume().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Prenume invalid: prenumele trebuie să conțină doar litere și spații.");
        }
        //Valideaza numarul de telefon: format international, intre 10 si 15 cifre
        if (!pacient.getNumarTelefon().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Număr de telefon invalid.");
        }
        //Valideaza CNP-ul: trebuie sa aiba exact 13 cifre
        if (!pacient.getCnp().matches("[0-9]{13}")) {
            throw new IllegalArgumentException("CNP invalid.");
        }
        //Valideaza sexul pacientului: poate fi doar 'M' sau 'F'
        if (!pacient.getSex().matches("[MF]")) {
            throw new IllegalArgumentException("Sex invalid: poate fi doar M sau F.");
        }
        //Valideaza strada: poate contine litere, cifre si caractere speciale
        if (!pacient.getStrada().matches("^[a-zA-Z0-9\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Stradă invalidă: strada poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Valideaza orasul: trebuie sa contina doar litere si spatii
        if (!pacient.getOras().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Oraș invalid: orașul poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Valideaza judetul: trebuie sa contina doar litere si spatii
        if (!pacient.getJudet().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Județ invalid: județul poate conține doar litere, cifre, spații și caractere românești.");
        }
        //Verifica daca data nasterii este prezenta
        if (pacient.getDataNasterii() == null) {
            throw new IllegalArgumentException("Data Nașterii este obligatorie.");
        }
        //Verifica daca data inrolarii in program este prezenta
        if (pacient.getDataInrolareProgram() == null) {
            throw new IllegalArgumentException("Data Înrolării în Program este obligatorie.");
        }
        //Verifica daca ID-ul medicului este prezent si valid
        if (pacient.getId_medic() == null) {
            throw new IllegalArgumentException("ID-ul medicului invalid: trebuie să selectați un medic.");
        }
        int medic = pacientDAO.findLastMedicId();
        if (medic < pacient.getId_medic()) {
            throw new IllegalArgumentException("Medicul nu există. Ultimul ID din baza de date este " + medic);
        }
        //Verifica daca ID-ul apartinatorului este prezent si valid
        int apartinator = pacientDAO.findLastApartinator();
        if (pacient.getId_apartinator() != null && apartinator < pacient.getId_apartinator()) {
            throw new IllegalArgumentException("Apartinatorul nu există. Ultimul ID din baza de date este " + apartinator);
        }
    }
}
