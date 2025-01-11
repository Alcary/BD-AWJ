/** Entitatea Admin cu atributele din tabel si gettere si settere pentru acestea
 * @autor Calaras Alexandru
 * @version 20 Decembrie 2024
 */
package com.example.Laborator_7.entity;

public class Admin {

    private int idAdmin;

    private String username;

    private String parola;

    private String rol;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
