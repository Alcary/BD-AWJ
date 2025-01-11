/** Entitatea Boli_Asociate cu atributele din tabel si gettere si settere pentru acestea
 * @author Calaras Alexandru
 * @version 15 Decembrie 2024
 */
package com.example.Laborator_7.entity;

public class Boli_Asociate {
    private int id_boala;

    private String nume;

    private String tratament;

    public int getId_boala() {
        return id_boala;
    }

    public void setId_boala(int id_boala) {
        this.id_boala = id_boala;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTratament() {
        return tratament;
    }

    public void setTratament(String tratament) {
        this.tratament = tratament;
    }
}