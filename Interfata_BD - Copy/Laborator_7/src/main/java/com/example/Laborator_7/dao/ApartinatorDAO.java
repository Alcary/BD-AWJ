package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Apartinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ApartinatorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Apartinator> getAllApartinatori() {
        String sql = "SELECT * FROM apartinatori ORDER BY id_apartinator";
        return jdbcTemplate.query(sql, new ApartinatorRowMapper());
    }

    public int updateApartinator(Apartinator apartinator) {
        String sql = "UPDATE apartinatori SET Nume = ?, Prenume = ?, NumarTelefon = ? " +
                "WHERE id_apartinator = ?";
        return jdbcTemplate.update(sql,
                apartinator.getNume(),
                apartinator.getPrenume(),
                apartinator.getNumarTelefon(),
                apartinator.getId_apartinator());
    }

    public int insertApartinator(Apartinator apartinator) {
        String sql = "INSERT INTO apartinatori (Nume, Prenume, NumarTelefon) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                apartinator.getNume(),
                apartinator.getPrenume(),
                apartinator.getNumarTelefon());
    }

    public List<Apartinator> findByNume(String nume) {
        String sql = "SELECT * FROM apartinatori WHERE nume LIKE " +
                "CONCAT('%', ?, '%') OR prenume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY id_apartinator";
        return jdbcTemplate.query(sql, new ApartinatorRowMapper(), nume, nume);
    }

    public Apartinator findById(int id) {
        String sql = "SELECT * FROM apartinatori WHERE id_apartinator = ? ORDER BY id_apartinator";
        return jdbcTemplate.queryForObject(sql, new ApartinatorRowMapper(), id);
    }

    public void deleteApartinator(int id) {
        String sql = "DELETE FROM apartinatori WHERE id_apartinator = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ApartinatorRowMapper implements RowMapper<Apartinator> {
        @Override
        public Apartinator mapRow(ResultSet rs, int rowNum) throws SQLException {
            Apartinator apartinator = new Apartinator();
            apartinator.setId_apartinator(rs.getInt("id_apartinator"));
            apartinator.setNume(rs.getString("Nume"));
            apartinator.setPrenume(rs.getString("Prenume"));
            apartinator.setNumarTelefon(rs.getString("NumarTelefon"));
            return apartinator;
        }
    }
}