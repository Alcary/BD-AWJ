//Entitatea Pacienti_Boli cu atributele din tabel si gettere si settere pentru acestea
package com.example.Laborator_7.entity;

public class Pacienti_Boli {
    private int idPacient;

    private int idBoala;

    private java.sql.Date dataAparitie;

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public int getIdBoala() {
        return idBoala;
    }

    public void setIdBoala(int idBoala) {
        this.idBoala = idBoala;
    }

    public java.sql.Date getDataAparitie() {
        return dataAparitie;
    }

    public void setDataAparitie(java.sql.Date dataAparitie) {
        this.dataAparitie = dataAparitie;
    }
}