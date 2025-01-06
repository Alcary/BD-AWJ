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

    public List<Medicamente> findByNume(String nume) {
        String sql = "SELECT M.*, C.Nume AS NumeCompanie FROM medicamente " +
                "M INNER JOIN companii_farmaceutice C ON M.id_companie = C.id_companie " +
                "WHERE M.nume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY M.id_medicament";
        return jdbcTemplate.query(sql, new MedicamentRowMapper(), nume);
    }

    public Medicamente findById(int id) {
        String sql = "SELECT M.*, C.Nume AS NumeCompanie FROM medicamente " +
                "M INNER JOIN companii_farmaceutice C ON M.id_companie = C.id_companie " +
                "WHERE M.id_medicament = ?";
        return jdbcTemplate.queryForObject(sql, new MedicamentRowMapper(), id);
    }

    public List<Medicamente> findMedicamentByCompanieOras(String oras) {
        String sql = "SELECT M.*, " +
                "C.Nume AS NumeCompanie " +
                "FROM medicamente M " +
                "INNER JOIN companii_farmaceutice C ON M.id_companie = C.id_companie " +
                "WHERE M.id_companie IN ( " +
                "   SELECT C.id_companie " +
                "   FROM companii_farmaceutice C " +
                "   WHERE C.oras LIKE CONCAT('%', ?, '%'))";
        return jdbcTemplate.query(sql, new MedicamentRowMapper(), oras);
    }

    public int insertMedicament(Medicamente medicament) {
        String sql = "INSERT INTO medicamente (nume, duratatratament, id_companie) " +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                medicament.getNumeMedicament(),
                medicament.getDurataTratament(),
                medicament.getIdCompanie());
    }

    public int updateMedicament(Medicamente medicament) {
        String sql = "UPDATE medicamente SET nume = ?, duratatratament = ?, id_companie = ? " +
                "WHERE id_medicament = ?";
        return jdbcTemplate.update(sql,
                medicament.getNumeMedicament(),
                medicament.getDurataTratament(),
                medicament.getIdCompanie(),
                medicament.getIdMedicament());
    }

    public void deleteMedicament(int id) {
        String sql = "DELETE FROM medicamente WHERE id_medicament = ?";
        jdbcTemplate.update(sql, id);
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