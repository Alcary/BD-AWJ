package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Medic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MedicDAO {
    private static final Logger logger = LoggerFactory.getLogger(MedicDAO.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Medic> getAllMedics() {
        String sql = "SELECT M.*, CONCAT(S.Nume, ' ', S.Prenume) AS numeSupervizor, "+
                "SP.Nume AS numeSpital " +
                "FROM medici M " +
                "LEFT JOIN medici S ON M.id_supervisor = S.id_medic " +
                "INNER JOIN spitale SP ON M.id_spital = SP.id_spital " +
                "ORDER BY M.ID_Medic";
        logger.debug("Executing SQL: {}", sql);
        return jdbcTemplate.query(sql, new MedicRowMapper());
    }

    public List<Medic> findByNume(String nume) {
        String sql = "SELECT M.*, CONCAT(S.Nume, ' ', S.Prenume) AS numeSupervizor, "+
                "SP.Nume AS numeSpital " +
                "FROM medici M " +
                "LEFT JOIN medici S ON M.id_supervisor = S.id_medic " +
                "INNER JOIN spitale SP ON M.id_spital = SP.id_spital " +
                "WHERE M.Nume LIKE CONCAT('%', ?, '%') OR M.Prenume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY M.ID_Medic";
        return jdbcTemplate.query(sql, new MedicRowMapper(), nume, nume);
    }

    public Medic findById(int id) {
        String sql = "SELECT M.*, CONCAT(S.Nume, ' ', S.Prenume) AS numeSupervizor, "+
                "SP.Nume AS numeSpital " +
                "FROM medici M " +
                "LEFT JOIN medici S ON M.id_supervisor = S.id_medic " +
                "INNER JOIN spitale SP ON M.id_spital = SP.id_spital " +
                "WHERE M.ID_Medic = ?";
        return jdbcTemplate.queryForObject(sql, new MedicRowMapper(), id);
    }

    public List<Medic> findMedicBySpital(String spital) {
        String sql = "SELECT M.*, " +
                "       CONCAT(S.Nume, ' ', S.Prenume) AS numeSupervizor, " +
                "       SP.Nume AS numeSpital " +
                "FROM medici M " +
                "LEFT JOIN medici S ON M.id_supervisor = S.id_medic " +
                "INNER JOIN spitale SP ON M.id_spital = SP.id_spital " +
                "WHERE M.id_spital IN ( " +
                "    SELECT S.id_spital " +
                "    FROM spitale S " +
                "    WHERE S.Nume LIKE CONCAT('%', ?, '%') " +
                ") " +
                "ORDER BY M.ID_Medic";
        return jdbcTemplate.query(sql, new MedicRowMapper(), spital);
    }

    public int insertMedic(Medic medic){
        String sql = "INSERT INTO medici (Nume, Prenume, CNP, Sex, numartelefon, " +
            "oras, judet, id_supervisor, id_spital) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                medic.getNume(),
                medic.getPrenume(),
                medic.getCnp(),
                medic.getSex(),
                medic.getNumarTelefon(),
                medic.getOras(),
                medic.getJudet(),
                medic.getIdSupervizor(),
                medic.getIdSpital());
    }

    public int updateMedic(Medic medic) {
        String sql = "UPDATE medici SET Nume = ?, Prenume = ?, CNP = ?, Sex = ?, " +
                "NumarTelefon = ?, Oras = ?, Judet = ?, ID_Supervisor = ?, " +
                "ID_Spital = ? WHERE ID_Medic = ?";
        return jdbcTemplate.update(sql,
                medic.getNume(),
                medic.getPrenume(),
                medic.getCnp(),
                medic.getSex(),
                medic.getNumarTelefon(),
                medic.getOras(),
                medic.getJudet(),
                medic.getIdSupervizor(),
                medic.getIdSpital(),
                medic.getIdMedic());
    }

    public void deleteMedic(int id) {
        String sql = "DELETE FROM medici WHERE ID_Medic = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class MedicRowMapper implements RowMapper<Medic> {
        @Override
        public Medic mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medic medic = new Medic();
            medic.setIdMedic(rs.getInt("ID_Medic"));
            medic.setNume(rs.getString("Nume"));
            medic.setPrenume(rs.getString("Prenume"));
            medic.setCnp(rs.getString("CNP"));
            medic.setSex(rs.getString("Sex"));
            medic.setNumarTelefon(rs.getString("NumarTelefon"));
            medic.setOras(rs.getString("Oras"));
            medic.setJudet(rs.getString("Judet"));
            medic.setIdSupervizor(rs.getInt("ID_Supervisor"));
            medic.setIdSpital(rs.getInt("ID_Spital"));
            medic.setNumeSupervizor(rs.getString("numeSupervizor"));
            medic.setNumeSpital(rs.getString("numeSpital"));
            return medic;
        }
    }
}
