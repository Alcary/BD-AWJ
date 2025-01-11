/** Entitatea Pacient cu atributele din tabel si gettere si settere pentru acestea
 * @author Calaras Alexandru
 * @version 16 Decembrie 2024
 */
package com.example.Laborator_7.entity;

import jakarta.annotation.Nullable;

public class Pacient {
    private int id_pacient;

    private String nume;

    private String prenume;

    private String cnp;

    private String sex;

    private java.sql.Date dataNasterii;

    private java.sql.Date dataInrolareProgram;

    private String strada;

    private String numarTelefon;

    private String oras;

    private String judet;

    @Nullable
    private Integer id_medic;

    @Nullable
    private Integer id_apartinator;

    private String numeMedic;

    private String numeApartinator;


    public int getId_pacient() {
        return id_pacient;
    }

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
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

    public java.sql.Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(java.sql.Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public java.sql.Date getDataInrolareProgram() {
        return dataInrolareProgram;
    }

    public void setDataInrolareProgram(java.sql.Date dataInrolareProgram) {
        this.dataInrolareProgram = dataInrolareProgram;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
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

    public Integer getId_medic() {
        return id_medic;
    }

    public void setId_medic(Integer id_medic) {
        this.id_medic = id_medic;
    }

    public Integer getId_apartinator() {
        return id_apartinator;
    }

    public void setId_apartinator(Integer id_apartinator) {
        this.id_apartinator = id_apartinator;
    }

    public String getNumeMedic() {
        return numeMedic;
    }

    public void setMedicNume(String numeMedic) {
        this.numeMedic = numeMedic;
    }

    public String getNumeApartinator() {
        return numeApartinator;
    }

    public void setApartinatorNume(String numeApartinator) {
        this.numeApartinator = numeApartinator;
    }
}