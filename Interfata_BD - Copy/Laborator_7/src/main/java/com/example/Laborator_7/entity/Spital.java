/** Entitatea Spital cu atributele din tabel si gettere si settere pentru acestea
 * @author Calaras Alexandru
 * @version 16 Decembrie 2024
 */
package com.example.Laborator_7.entity;

public class Spital {
    private int idSpital;
    private String nume;
    private String strada;
    private String oras;
    private String judet;
    private String numarTelefon;

    public int getIdSpital() {
        return idSpital;
    }

    public void setIdSpital(int idSpital) {
        this.idSpital = idSpital;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }
}
