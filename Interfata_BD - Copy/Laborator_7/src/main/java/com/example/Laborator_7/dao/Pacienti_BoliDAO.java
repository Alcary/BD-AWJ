package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Pacienti_Boli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Pacienti_BoliDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pacienti_Boli> getAllPacientiBoli() {
        String sql = "SELECT * FROM Pacienti_Boli";
        return jdbcTemplate.query(sql, new PacientiBoliRowMapper());
    }

    public int savePacientiBoli(Pacienti_Boli pacientiBoli) {
        String sql = "INSERT INTO Pacienti_Boli (ID_Pacient, ID_Boala, DataAparitie) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                pacientiBoli.getIdPacient(),
                pacientiBoli.getIdBoala(),
                pacientiBoli.getDataAparitie());
    }

    private static class PacientiBoliRowMapper implements RowMapper<Pacienti_Boli> {
        @Override
        public Pacienti_Boli mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pacienti_Boli pacientiBoli = new Pacienti_Boli();
            pacientiBoli.setIdPacient(rs.getInt("ID_Pacient"));
            pacientiBoli.setIdBoala(rs.getInt("ID_Boala"));
            pacientiBoli.setDataAparitie(rs.getDate("DataAparitie"));
            return pacientiBoli;
        }
    }
}
