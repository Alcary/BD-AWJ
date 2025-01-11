/** Realizeaza interogarile SQL pentru Apartinatori
 * @author Calaras Alexandru
 * @version 29 Decembrie 2024
 */
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

    //Returneaza toti apartinatorii din baza de date, ordonati dupa id_apartinator
    public List<Apartinator> getAllApartinatori() {
        String sql = "SELECT * FROM apartinatori ORDER BY id_apartinator";
        return jdbcTemplate.query(sql, new ApartinatorRowMapper());
    }

    //Actualizeaza un apartinator existent in baza de date
    public int updateApartinator(Apartinator apartinator) {
        String sql = "UPDATE apartinatori SET Nume = ?, Prenume = ?, NumarTelefon = ? " +
                "WHERE id_apartinator = ?";
        return jdbcTemplate.update(sql,
                apartinator.getNume(),
                apartinator.getPrenume(),
                apartinator.getNumarTelefon(),
                apartinator.getId_apartinator());
    }

    //Insereaza un nou apartinator in baza de date
    public int insertApartinator(Apartinator apartinator) {
        String sql = "INSERT INTO apartinatori (Nume, Prenume, NumarTelefon) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                apartinator.getNume(),
                apartinator.getPrenume(),
                apartinator.getNumarTelefon());
    }

    //Cauta apartinatori dupa nume sau prenume (partial sau complet)
    public List<Apartinator> findByNume(String nume) {
        String sql = "SELECT * FROM apartinatori WHERE nume LIKE " +
                "CONCAT('%', ?, '%') OR prenume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY id_apartinator";
        return jdbcTemplate.query(sql, new ApartinatorRowMapper(), nume, nume);
    }

    //Cauta un apartinator dupa ID-ul sau unic
    public Apartinator findById(int id) {
        String sql = "SELECT * FROM apartinatori WHERE id_apartinator = ? ORDER BY id_apartinator";
        return jdbcTemplate.queryForObject(sql, new ApartinatorRowMapper(), id);
    }

    //Sterge un apartinator din baza de date folosind ID-ul acestuia
    public void deleteApartinator(int id) {
        String sql = "DELETE FROM apartinatori WHERE id_apartinator = ?";
        jdbcTemplate.update(sql, id);
    }

    //RowMapper personalizat pentru a mapa rezultatele din baza de date in obiecte Apartinator
    private static class ApartinatorRowMapper implements RowMapper<Apartinator> {
        @Override
        public Apartinator mapRow(ResultSet rs, int rowNum) throws SQLException {
            Apartinator apartinator = new Apartinator();
            apartinator.setId_apartinator(rs.getInt("id_apartinator")); //Seteaza ID-ul apartinatorului
            apartinator.setNume(rs.getString("Nume")); //Seteaza numele
            apartinator.setPrenume(rs.getString("Prenume")); //Seteaza prenumele
            apartinator.setNumarTelefon(rs.getString("NumarTelefon")); //Seteaza numarul de telefon
            return apartinator;
        }
    }
}
