/** Entitatea CompaniiFarmaceutice cu atributele din tabel si gettere si settere pentru acestea
 * @author Calaras Alexandru
 * @version 16 Decembrie 2024
 */
package com.example.Laborator_7.entity;

public class CompaniiFarmaceutice {
    private int idCompanieFarmaceutica;

    private String nume;

    private String strada;

    private String oras;

    private String tara;

    private String numarTelefon;

    public void setIdCompanieFarmaceutica(int idCompanieFarmaceutica) {
        this.idCompanieFarmaceutica = idCompanieFarmaceutica;
    }

    public int getIdCompanieFarmaceutica() {
        return idCompanieFarmaceutica;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getStrada() {
        return strada;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getOras() {
        return oras;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getTara() {
        return tara;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }
}
