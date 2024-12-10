package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.entity.Medicamente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MedicamenteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Medicamente> getAllMedicamente() {
        String sql = "SELECT M.*, C.Nume AS NumeCompanie FROM medicamente " +
                "M INNER JOIN companii_farmaceutice C ON M.id_companie = C.id_companie " +
                "ORDER BY M.id_medicament";
        return jdbcTemplate.query(sql, new MedicamentRowMapper());
    }

    public int saveMedicamente(Medicamente medicament) {
        String sql = "INSERT INTO medicamente (id_medicament, nume, duratatratament, id_companie) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                medicament.getIdMedicament(),
                medicament.getNumeMedicament(),
                medicament.getDurataTratament(),
                medicament.getIdCompanie());
    }

    public List<Medicamente> findByNume(String nume) {
        String sql = "SELECT * FROM medicamente WHERE nume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY id_medicament";
        return jdbcTemplate.query(sql, new MedicamentRowMapper(), nume);
    }

    private static class MedicamentRowMapper implements RowMapper<Medicamente> {
        @Override
        public Medicamente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medicamente medicament = new Medicamente();
            medicament.setIdMedicament(rs.getInt("id_medicament"));
            medicament.setNumeMedicament(rs.getString("nume"));
            medicament.setDurataTratament(rs.getInt("duratatratament"));
            medicament.setIdCompanie(rs.getInt("id_Companie"));
            medicament.setNumeCompanie(rs.getString("NumeCompanie"));
            return medicament;
        }
    }
}