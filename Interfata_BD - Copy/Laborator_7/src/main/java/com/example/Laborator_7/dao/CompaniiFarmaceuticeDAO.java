/**Realizeaza interogarile SQL pentru Companii_Farmaceutice
 * @author Calaras Alexandru
 * @version 29 Decembrie 2024
 */
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

    //Returneaza toate companiile farmaceutice din baza de date, ordonate dupa id_companie
    public List<CompaniiFarmaceutice> getAllCompanii() {
        String sql = "SELECT * FROM companii_farmaceutice ORDER BY id_companie";
        return jdbcTemplate.query(sql, new CompanieRowMapper());
    }

    //Insereaza o noua companie farmaceutica in baza de date
    public int insertCompanie(CompaniiFarmaceutice companie) {
        String sql = "INSERT INTO companii_farmaceutice (Nume, Strada, Oras, Tara) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                companie.getNume(),
                companie.getStrada(),
                companie.getOras(),
                companie.getTara());
    }

    //Cauta companii farmaceutice dupa nume (partial sau complet)
    public List<CompaniiFarmaceutice> findByNume(String nume) {
        String sql = "SELECT * FROM companii_farmaceutice WHERE nume LIKE CONCAT('%', ?, '%')";
        return jdbcTemplate.query(sql, new CompanieRowMapper(), nume);
    }

    //Cauta o companie farmaceutica dupa ID-ul sau unic
    public CompaniiFarmaceutice findById(int id) {
        String sql = "SELECT * FROM companii_farmaceutice WHERE id_companie = ?";
        return jdbcTemplate.queryForObject(sql, new CompanieRowMapper(), id);
    }

    //Actualizeaza o companie farmaceutica existenta in baza de date
    public void updateCompanie(CompaniiFarmaceutice companie) {
        String sql = "UPDATE companii_farmaceutice SET Nume = ?, Strada = ?, Oras = ?, Tara = ? WHERE id_companie = ?";
        jdbcTemplate.update(sql,
                companie.getNume(),
                companie.getStrada(),
                companie.getOras(),
                companie.getTara(),
                companie.getIdCompanieFarmaceutica());
    }

    //Sterge o companie farmaceutica din baza de date folosind ID-ul acesteia
    public void deleteCompanie(int id) {
        String sql = "DELETE FROM companii_farmaceutice WHERE id_companie = ?";
        jdbcTemplate.update(sql, id);
    }

    //RowMapper personalizat pentru a mapa rezultatele din baza de date la obiecte de tip CompaniiFarmaceutice
    private static class CompanieRowMapper implements RowMapper<CompaniiFarmaceutice> {
        @Override
        public CompaniiFarmaceutice mapRow(ResultSet rs, int rowNum) throws SQLException {
            CompaniiFarmaceutice companie = new CompaniiFarmaceutice();
            companie.setIdCompanieFarmaceutica(rs.getInt("id_companie")); //Seteaza ID-ul companiei
            companie.setNume(rs.getString("Nume")); //Seteaza numele companiei
            companie.setStrada(rs.getString("Strada")); //Seteaza strada companiei
            companie.setOras(rs.getString("Oras")); //Seteaza orasul companiei
            companie.setTara(rs.getString("Tara")); //Seteaza tara companiei
            return companie;
        }
    }
}