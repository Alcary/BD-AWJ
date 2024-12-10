package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Pacienti_Medicamente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Pacienti_MedicamenteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pacienti_Medicamente> getAllPacientiMedicamente() {
        String sql = "SELECT * FROM pacienti_medicamente";
        return jdbcTemplate.query(sql, new PacientiMedicamenteRowMapper());
    }

    public int savePacientMedicament(Pacienti_Medicamente pacientiMedicamente) {
        String sql = "INSERT INTO pacienti_medicamente (ID_Pacient, ID_Medicament, DataStartTratament, DataFinalizareTratament) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                pacientiMedicamente.getID_Pacient(),
                pacientiMedicamente.getID_Medicament(),
                pacientiMedicamente.getDataStartTratament(),
                pacientiMedicamente.getDataFinalizareTratament());
    }

    private static class PacientiMedicamenteRowMapper implements RowMapper<Pacienti_Medicamente> {
        @Override
        public Pacienti_Medicamente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pacienti_Medicamente pacientiMedicamente = new Pacienti_Medicamente();
            pacientiMedicamente.setID_Pacient(rs.getInt("ID_Pacient"));
            pacientiMedicamente.setID_Medicament(rs.getInt("ID_Medicament"));
            pacientiMedicamente.setDataStartTratament(rs.getDate("DataStartTratament"));
            pacientiMedicamente.setDataFinalizareTratament(rs.getDate("DataFinalizareTratament"));
            return pacientiMedicamente;
        }
    }
}
