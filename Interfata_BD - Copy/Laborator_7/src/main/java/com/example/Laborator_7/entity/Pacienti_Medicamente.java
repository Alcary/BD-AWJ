//Entitatea Pacienti_Medicamente cu atributele din tabel si gettere si settere pentru acestea
package com.example.Laborator_7.entity;

public class Pacienti_Medicamente {
    private int ID_Pacient;

    private int ID_Medicament;

    private java.sql.Date DataStartTratament;

    private java.sql.Date DataFinalizareTratament;

    public int getID_Pacient() {
        return ID_Pacient;
    }

    public void setID_Pacient(int ID_Pacient) {
        this.ID_Pacient = ID_Pacient;
    }

    public int getID_Medicament() {
        return ID_Medicament;
    }

    public void setID_Medicament(int ID_Medicament) {
        this.ID_Medicament = ID_Medicament;
    }

    public java.sql.Date getDataStartTratament() {
        return DataStartTratament;
    }

    public void setDataStartTratament(java.sql.Date DataStartTratament) {
        this.DataStartTratament = DataStartTratament;
    }

    public java.sql.Date getDataFinalizareTratament() {
        return DataFinalizareTratament;
    }

    public void setDataFinalizareTratament(java.sql.Date DataFinalizareTratament) {
        this.DataFinalizareTratament = DataFinalizareTratament;
    }

}
