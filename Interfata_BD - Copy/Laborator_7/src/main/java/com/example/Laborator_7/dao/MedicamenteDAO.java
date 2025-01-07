//Realizeaza interogarile SQL pentru Medicamente
package com.example.Laborator_7.dao;

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

    //Returneaza toate medicamentele, inclusiv numele companiei farmaceutice asociate
    public List<Medicamente> getAllMedicamente() {
        String sql = "SELECT M.*, C.Nume AS NumeCompanie FROM medicamente " +
                "M INNER JOIN companii_farmaceutice C ON M.id_companie = C.id_companie " +
                "ORDER BY M.id_medicament";
        return jdbcTemplate.query(sql, new MedicamentRowMapper());
    }

    //Cauta medicamente dupa nume si returneaza lista rezultatelor
    public List<Medicamente> findByNume(String nume) {
        String sql = "SELECT M.*, C.Nume AS NumeCompanie FROM medicamente " +
                "M INNER JOIN companii_farmaceutice C ON M.id_companie = C.id_companie " +
                "WHERE M.nume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY M.id_medicament";
        return jdbcTemplate.query(sql, new MedicamentRowMapper(), nume);
    }

    //Cauta un medicament dupa ID-ul sau unic
    public Medicamente findById(int id) {
        String sql = "SELECT M.*, C.Nume AS NumeCompanie FROM medicamente " +
                "M INNER JOIN companii_farmaceutice C ON M.id_companie = C.id_companie " +
                "WHERE M.id_medicament = ?";
        return jdbcTemplate.queryForObject(sql, new MedicamentRowMapper(), id);
    }

    //Cauta medicamente produse de companii localizate intr-un oras specific
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

    //Insereaza un nou medicament in baza de date
    public int insertMedicament(Medicamente medicament) {
        String sql = "INSERT INTO medicamente (nume, duratatratament, id_companie) " +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                medicament.getNumeMedicament(),
                medicament.getDurataTratament(),
                medicament.getIdCompanie());
    }

    //Actualizeaza un medicament existent in baza de date
    public int updateMedicament(Medicamente medicament) {
        String sql = "UPDATE medicamente SET nume = ?, duratatratament = ?, id_companie = ? " +
                "WHERE id_medicament = ?";
        return jdbcTemplate.update(sql,
                medicament.getNumeMedicament(),
                medicament.getDurataTratament(),
                medicament.getIdCompanie(),
                medicament.getIdMedicament());
    }

    //Sterge un medicament din baza de date folosind ID-ul acestuia
    public void deleteMedicament(int id) {
        String sql = "DELETE FROM medicamente WHERE id_medicament = ?";
        jdbcTemplate.update(sql, id);
    }

    //Returneaza cel mai mare ID al companiei farmaceutice din baza de date
    public int findLastIdCompanie() {
        String sql = "SELECT MAX(id_companie) FROM companii_farmaceutice";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //RowMapper personalizat pentru a mapa rezultatele din baza de date la obiecte de tip Medicamente
    private static class MedicamentRowMapper implements RowMapper<Medicamente> {
        @Override
        public Medicamente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medicamente medicament = new Medicamente();
            medicament.setIdMedicament(rs.getInt("id_medicament")); //Seteaza ID-ul medicamentului
            medicament.setNumeMedicament(rs.getString("nume")); //Seteaza numele medicamentului
            medicament.setDurataTratament(rs.getInt("duratatratament")); //Seteaza durata tratamentului
            medicament.setIdCompanie(rs.getInt("id_Companie")); //Seteaza ID-ul companiei
            medicament.setNumeCompanie(rs.getString("NumeCompanie")); //Seteaza numele companiei
            return medicament;
        }
    }
}
