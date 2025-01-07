//Realizeaza interogarile SQL pentru Boli_Asociate
package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Boli_Asociate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Boli_AsociateDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Returneaza toate bolile asociate din baza de date, ordonate dupa id_boala
    public List<Boli_Asociate> getAllBoli() {
        String sql = "SELECT * FROM boli_asociate ORDER BY id_boala";
        return jdbcTemplate.query(sql, new BoliAsociateRowMapper());
    }

    //Clase interna RowMapper pentru maparea rezultatelor din baza de date la obiecte de tip Boli_Asociate
    private static class BoliAsociateRowMapper implements RowMapper<Boli_Asociate> {
        @Override
        public Boli_Asociate mapRow(ResultSet rs, int rowNum) throws SQLException {
            Boli_Asociate boliAsociate = new Boli_Asociate();
            boliAsociate.setId_boala(rs.getInt("id_boala")); //Seteaza ID-ul bolii
            boliAsociate.setNume(rs.getString("Nume")); //Seteaza numele bolii
            boliAsociate.setTratament(rs.getString("Tratament")); //Seteaza tratamentul asociat
            return boliAsociate;
        }
    }

    //Insereaza o noua boala asociata in baza de date
    public int insertBoalaAsociata(Boli_Asociate boliAsociate) {
        String sql = "INSERT INTO boli_asociate (Nume, Tratament) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                boliAsociate.getNume(),
                boliAsociate.getTratament());
    }

    //Actualizeaza o boala asociata existenta in baza de date
    public int updateBoli(Boli_Asociate boliAsociate) {
        String sql = "UPDATE boli_asociate SET Nume = ?, Tratament = ? WHERE id_boala = ?";
        return jdbcTemplate.update(sql,
                boliAsociate.getNume(),
                boliAsociate.getTratament(),
                boliAsociate.getId_boala());
    }

    //Cauta o boala asociata dupa ID-ul sau unic
    public Boli_Asociate findById(int id) {
        String sql = "SELECT * FROM boli_asociate WHERE id_boala = ?";
        return jdbcTemplate.queryForObject(sql, new BoliAsociateRowMapper(), id);
    }

    //Sterge o boala asociata din baza de date folosind ID-ul acesteia
    public void deleteBoli(int id) {
        String sql = "DELETE FROM boli_asociate WHERE ID_Boala = ?";
        jdbcTemplate.update(sql, id);
    }

    //Cauta boli asociate dupa nume (partial sau complet)
    public List<Boli_Asociate> findByNume(String nume) {
        String sql = "SELECT * FROM boli_asociate WHERE nume LIKE CONCAT('%', ?, '%') ORDER BY id_boala";
        return jdbcTemplate.query(sql, new BoliAsociateRowMapper(), nume);
    }
}

