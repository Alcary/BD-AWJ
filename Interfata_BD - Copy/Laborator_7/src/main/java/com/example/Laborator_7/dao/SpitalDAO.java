package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Admin;
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

    public List<Spital> getAllHospitals() {
        String sql = "SELECT * FROM spitale ORDER BY id_spital";
        return jdbcTemplate.query(sql, new SpitalRowMapper());
    }

    public List<Spital> findByNume(String nume) {
        String sql = "SELECT * FROM spitale WHERE nume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY id_spital";
        return jdbcTemplate.query(sql, new SpitalRowMapper(), nume);
    }

    public Spital findById(int id) {
        String sql = "SELECT * FROM spitale WHERE id_spital = ?";
        return jdbcTemplate.queryForObject(sql, new SpitalRowMapper(), id);
    }

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

    public List<Spital> findTopSpitale() {
        String sql = "SELECT S.* " +
                "FROM spitale S " +
                "ORDER BY (SELECT COUNT(*) " +
                "          FROM medici M " +
                "          WHERE M.id_spital = S.id_spital) DESC " +
                "LIMIT 3";

        return jdbcTemplate.query(sql, new SpitalRowMapper());
    }

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

    public void deleteSpital(int id) {
        String sql = "DELETE FROM spitale WHERE id_spital = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class SpitalRowMapper implements RowMapper<Spital> {
        @Override
        public Spital mapRow(ResultSet rs, int rowNum) throws SQLException {
            Spital spital = new Spital();
            spital.setIdSpital(rs.getInt("id_spital"));
            spital.setNume(rs.getString("Nume"));
            spital.setStrada(rs.getString("Strada"));
            spital.setOras(rs.getString("Oras"));
            spital.setJudet(rs.getString("Judet"));
            spital.setNumarTelefon(rs.getString("NumarTelefon"));
            return spital;
        }
    }
}