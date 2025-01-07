//Serviciu care gestioneaza logica aplicatiei pentru entitatea Medicamente
package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.MedicamenteDAO;
import com.example.Laborator_7.entity.Medicamente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamenteService {

    @Autowired
    private MedicamenteDAO medicamenteDAO;

    //Returneaza toate medicamentele din baza de date
    public List<Medicamente> getAllMedicamente() {
        return medicamenteDAO.getAllMedicamente();
    }

    //Cauta medicamente dupa nume
    public List<Medicamente> findByNume(String nume) {
        return medicamenteDAO.findByNume(nume);
    }

    //Insereaza un nou medicament in baza de date
    public int insertMedicament(Medicamente medicament) {
        return medicamenteDAO.insertMedicament(medicament);
    }

    //Actualizeaza datele unui medicament existent
    public int updateMedicament(Medicamente medicament) {
        return medicamenteDAO.updateMedicament(medicament);
    }

    //Gaseste un medicament dupa ID-ul unic
    public Medicamente findById(int id) {
        return medicamenteDAO.findById(id);
    }

    //Cauta medicamente produse de companii localizate intr-un oras specific
    public List<Medicamente> findMedicamentByCompanieOras(String oras) {
        return medicamenteDAO.findMedicamentByCompanieOras(oras);
    }

    //Sterge un medicament din baza de date folosind ID-ul acestuia
    public void deleteMedicament(int id) {
        medicamenteDAO.deleteMedicament(id);
    }

    //Valideaza datele unui medicament inainte de a fi inserate sau actualizate
    public void validateMedicament(Medicamente medicamente) {
        //Valideaza numele medicamentului: trebuie sa contina doar litere si spatii
        if (!medicamente.getNumeMedicament().matches("^[a-zA-Z\\s.\\-șȘțȚăĂîÎâÂ]+$")) {
            throw new IllegalArgumentException("Nume invalid: numele trebuie să conțină doar litere și spații.");
        }
        //Valideaza durata tratamentului: trebuie sa fie un numar pozitiv
        if (medicamente.getDurataTratament() <= 0) {
            throw new IllegalArgumentException("Durată invalidă: durata tratamentului trebuie să fie un număr pozitiv.");
        }
        //Valideaza ID-ul companiei: trebuie sa fie selectat
        if (medicamente.getIdCompanie() == null) {
            throw new IllegalArgumentException("ID-ul companiei invalid: trebuie să selectați o companie.");
        }
        //Verifica daca ID-ul companiei exista in baza de date
        int companie = medicamenteDAO.findLastIdCompanie();
        if (companie < medicamente.getIdCompanie()) {
            throw new IllegalArgumentException("ID-ul companiei nu există în baza de date. Ultimul ID este " + companie + ".");
        }
    }
}
