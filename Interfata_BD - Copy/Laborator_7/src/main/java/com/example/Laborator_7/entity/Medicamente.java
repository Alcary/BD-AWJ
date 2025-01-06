package com.example.Laborator_7.entity;

import jakarta.annotation.Nullable;

public class Medicamente {
    private int idMedicament;

    private String nume;

    private int durataTratament;

    private String numeCompanie;

    @Nullable
    private Integer idCompanie;

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public String getNumeMedicament() {
        return nume;
    }

    public void setNumeMedicament(String nume) {
        this.nume = nume;
    }

    public void setDurataTratament(int durataTratament) {
        this.durataTratament = durataTratament;
    }

    public int getDurataTratament() {
        return durataTratament;
    }

    public void setIdCompanie(Integer idCompanie) {
        this.idCompanie = idCompanie;
    }

    public Integer getIdCompanie() {
        return idCompanie;
    }

    public String getNumeCompanie() {
        return numeCompanie;
    }

    public void setNumeCompanie(String numeCompanie) {
        this.numeCompanie = numeCompanie;
    }
}
