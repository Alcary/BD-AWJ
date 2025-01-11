/** Realizeaza interogarile SQL pentru Spitale
 * @author Calaras Alexandru
 * @version 9 Ianuarie 2025
 */
package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Spital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SpitalDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Returneaza toate spitalele din baza de date, ordonate dupa id_spital
    public List<Spital> getAllHospitals() {
        String sql = "SELECT * FROM spitale ORDER BY id_spital";
        return jdbcTemplate.query(sql, new SpitalRowMapper());
    }

    //Cauta spitale dupa nume (partial sau complet)
    public List<Spital> findByNume(String nume) {
        String sql = "SELECT * FROM spitale WHERE nume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY id_spital";
        return jdbcTemplate.query(sql, new SpitalRowMapper(), nume);
    }

    //Cauta un spital dupa ID-ul sau unic
    public Spital findById(int id) {
        String sql = "SELECT * FROM spitale WHERE id_spital = ?";
        return jdbcTemplate.queryForObject(sql, new SpitalRowMapper(), id);
    }

    //Insereaza un nou spital in baza de date
    public int insertSpital(Spital spital) {
        String sql = "INSERT INTO spitale (Nume, Strada, Oras, Judet, " +
                "NumarTelefon) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                spital.getNume(),
                spital.getStrada(),
                spital.getOras(),
                spital.getJudet(),
                spital.getNumarTelefon());
    }

    //Returneaza top 3 spitale in functie de numarul de medici asociati
    public List<Spital> findTopSpitale() {
        String sql = "SELECT S.* " +
                "FROM spitale S " +
                "ORDER BY (SELECT COUNT(*) " +
                "          FROM medici M " +
                "          WHERE M.id_spital = S.id_spital) DESC " +
                "LIMIT 3";
        return jdbcTemplate.query(sql, new SpitalRowMapper());
    }

    //Actualizeaza un spital existent in baza de date
    public int updateSpital(Spital spital) {
        String sql = "UPDATE spitale SET Nume = ?, Strada = ?, Oras = ?, Judet = ?, " +
                "NumarTelefon = ? WHERE id_spital = ?";
        return jdbcTemplate.update(sql,
                spital.getNume(),
                spital.getStrada(),
                spital.getOras(),
                spital.getJudet(),
                spital.getNumarTelefon(),
                spital.getIdSpital());
    }

    //Sterge un spital din baza de date folosind ID-ul acestuia
    public void deleteSpital(int id) {
        String sql = "DELETE FROM spitale WHERE id_spital = ?";
        jdbcTemplate.update(sql, id);
    }

    //RowMapper pentru maparea rezultatelor din baza de date la obiecte de tip Spital
    private static class SpitalRowMapper implements RowMapper<Spital> {
        @Override
        public Spital mapRow(ResultSet rs, int rowNum) throws SQLException {
            Spital spital = new Spital();
            spital.setIdSpital(rs.getInt("id_spital")); //Seteaza ID-ul spitalului
            spital.setNume(rs.getString("Nume")); //Seteaza numele spitalului
            spital.setStrada(rs.getString("Strada")); //Seteaza strada spitalului
            spital.setOras(rs.getString("Oras")); //Seteaza orasul spitalului
            spital.setJudet(rs.getString("Judet")); //Seteaza judetul spitalului
            spital.setNumarTelefon(rs.getString("NumarTelefon")); //Seteaza numarul de telefon al spitalului
            return spital;
        }
    }
}
