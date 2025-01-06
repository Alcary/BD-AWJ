package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.CompaniiFarmaceutice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CompaniiFarmaceuticeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CompaniiFarmaceutice> getAllCompanii() {
        String sql = "SELECT * FROM companii_farmaceutice ORDER BY id_companie";
        return jdbcTemplate.query(sql, new CompanieRowMapper());
    }

    public int insertCompanie(CompaniiFarmaceutice companie) {
        String sql = "INSERT INTO companii_farmaceutice (Nume, Strada, Oras, Tara) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                companie.getNume(),
                companie.getStrada(),
                companie.getOras(),
                companie.getTara());}

    public List<CompaniiFarmaceutice> findByNume(String nume) {
        String sql = "SELECT * FROM companii_farmaceutice WHERE nume LIKE CONCAT('%', ?, '%')";
        return jdbcTemplate.query(sql, new CompanieRowMapper(), nume);
    }

    public CompaniiFarmaceutice findById(int id) {
        String sql = "SELECT * FROM companii_farmaceutice WHERE id_companie = ?";
        return jdbcTemplate.queryForObject(sql, new CompanieRowMapper(), id);
    }

    public void updateCompanie(CompaniiFarmaceutice companie) {
        String sql = "UPDATE companii_farmaceutice SET Nume = ?, Strada = ?, Oras = ?, Tara = ? WHERE id_companie = ?";
        jdbcTemplate.update(sql,
                companie.getNume(),
                companie.getStrada(),
                companie.getOras(),
                companie.getTara(),
                companie.getIdCompanieFarmaceutica());
    }

    public void deleteCompanie(int id) {
        String sql = "DELETE FROM companii_farmaceutice WHERE id_companie = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CompanieRowMapper implements RowMapper<CompaniiFarmaceutice> {
        @Override
        public CompaniiFarmaceutice mapRow(ResultSet rs, int rowNum) throws SQLException {
            CompaniiFarmaceutice companie = new CompaniiFarmaceutice();
            companie.setIdCompanieFarmaceutica(rs.getInt("id_companie"));
            companie.setNume(rs.getString("Nume"));
            companie.setStrada(rs.getString("Strada"));
            companie.setOras(rs.getString("Oras"));
            companie.setTara(rs.getString("Tara"));
            return companie;
        }
    }
}