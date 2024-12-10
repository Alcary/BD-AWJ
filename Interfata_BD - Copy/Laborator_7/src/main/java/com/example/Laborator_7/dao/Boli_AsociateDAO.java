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

    public List<Boli_Asociate> getAllBoli() {
        String sql = "SELECT * FROM boli_asociate ORDER BY id_boala";
        return jdbcTemplate.query(sql, new BoliAsociateRowMapper());
    }


    private static class BoliAsociateRowMapper implements RowMapper<Boli_Asociate> {
        @Override
        public Boli_Asociate mapRow(ResultSet rs, int rowNum) throws SQLException {
            Boli_Asociate boliAsociate = new Boli_Asociate();
            boliAsociate.setId_boala(rs.getInt("id_boala"));
            boliAsociate.setNume(rs.getString("Nume"));
            boliAsociate.setTratament(rs.getString("Tratament"));
            return boliAsociate;
        }
    }

    public int insertBoalaAsociata(Boli_Asociate boliAsociate) {
        String sql = "INSERT INTO boli_asociate (Nume, Tratament) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                boliAsociate.getNume(),
                boliAsociate.getTratament());
    }

    public int updateBoli(Boli_Asociate boliAsociate) {
        String sql = "UPDATE boli_asociate SET Nume = ?, Tratament = ? WHERE id_boala = ?";
        return jdbcTemplate.update(sql,
                boliAsociate.getNume(),
                boliAsociate.getTratament(),
                boliAsociate.getId_boala());
    }

    public Boli_Asociate findById(int id) {
        String sql = "SELECT * FROM boli_asociate WHERE id_boala = ?";
        return jdbcTemplate.queryForObject(sql, new BoliAsociateRowMapper(), id);
    }

    public void deleteBoli(int id) {
        String sql = "DELETE FROM boli_asociate WHERE ID_Boala = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Boli_Asociate> findByNume(String nume) {
        String sql = "SELECT * FROM boli_asociate WHERE nume LIKE CONCAT('%', ?, '%') ORDER BY id_boala";
        return jdbcTemplate.query(sql, new BoliAsociateRowMapper(), nume);
    }
}
