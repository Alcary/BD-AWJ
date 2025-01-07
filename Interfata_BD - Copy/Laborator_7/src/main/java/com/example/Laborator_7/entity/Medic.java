//Entitatea Medic cu atributele din tabel si gettere si settere pentru acestea
package com.example.Laborator_7.entity;

import jakarta.annotation.Nullable;

public class Medic {
    private int idMedic;

    private String nume;

    private String prenume;

    private String cnp;

    private String sex;

    private String numarTelefon;

    private String oras;

    private String judet;

    @Nullable
    private Integer idSupervizor;

    @Nullable
    private Integer idSpital;

    private String numeSpital;

    private String numeSupervizor;

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
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

    public Integer getIdSupervizor() {
        return idSupervizor;
    }

    public void setIdSupervizor(Integer idSupervizor) {
        this.idSupervizor = idSupervizor;
    }

    public Integer getIdSpital() {
        return idSpital;
    }

    public void setIdSpital(Integer idSpital) {
        this.idSpital = idSpital;
    }

    public String getNumeSpital() {
        return numeSpital;
    }

    public void setNumeSpital(String numeSpital) {
        this.numeSpital = numeSpital;
    }

    public String getNumeSupervizor() {
        return numeSupervizor;
    }

    public void setNumeSupervizor(String numeSupervizor) {
        this.numeSupervizor = numeSupervizor;
    }
}