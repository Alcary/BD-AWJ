/** Realizeaza interogarile SQL pentru Medici
 * @author Calaras Alexandru
 * @version 7 Ianuarie 2025
 */
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

    //Logger pentru a urmari executiile de SQL
    private static final Logger logger = LoggerFactory.getLogger(MedicDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Returneaza toti medicii, inclusiv numele supervizorului si spitalul asociat
    public List<Medic> getAllMedics() {
        String sql = "SELECT M.*, CONCAT(S.Nume, ' ', S.Prenume) AS numeSupervizor, " +
                "SP.Nume AS numeSpital " +
                "FROM medici M " +
                "LEFT JOIN medici S ON M.id_supervisor = S.id_medic " +
                "INNER JOIN spitale SP ON M.id_spital = SP.id_spital " +
                "ORDER BY M.ID_Medic";
        logger.debug("Executing SQL: {}", sql); //Logheaza executia SQL
        return jdbcTemplate.query(sql, new MedicRowMapper());
    }

    //Cauta medici dupa nume sau prenume si returneaza lista rezultatelor
    public List<Medic> findByNume(String nume) {
        String sql = "SELECT M.*, CONCAT(S.Nume, ' ', S.Prenume) AS numeSupervizor, " +
                "SP.Nume AS numeSpital " +
                "FROM medici M " +
                "LEFT JOIN medici S ON M.id_supervisor = S.id_medic " +
                "INNER JOIN spitale SP ON M.id_spital = SP.id_spital " +
                "WHERE M.Nume LIKE CONCAT('%', ?, '%') OR M.Prenume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY M.ID_Medic";
        return jdbcTemplate.query(sql, new MedicRowMapper(), nume, nume);
    }

    //Cauta un medic dupa ID-ul sau unic
    public Medic findById(int id) {
        String sql = "SELECT M.*, CONCAT(S.Nume, ' ', S.Prenume) AS numeSupervizor, " +
                "SP.Nume AS numeSpital " +
                "FROM medici M " +
                "LEFT JOIN medici S ON M.id_supervisor = S.id_medic " +
                "INNER JOIN spitale SP ON M.id_spital = SP.id_spital " +
                "WHERE M.ID_Medic = ?";
        return jdbcTemplate.queryForObject(sql, new MedicRowMapper(), id);
    }

    //Cauta medici care lucreaza intr-un spital specific
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

    //Insereaza un nou medic in baza de date
    public int insertMedic(Medic medic) {
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

    //Actualizeaza un medic existent in baza de date
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

    //Sterge un medic din baza de date folosind ID-ul acestuia
    public void deleteMedic(int id) {
        String sql = "DELETE FROM medici WHERE ID_Medic = ?";
        jdbcTemplate.update(sql, id);
    }

    //Returneaza ID-ul maxim al unui spital din baza de date
    public int findLastIdSpital() {
        String sql = "SELECT MAX(id_spital) FROM spitale";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //RowMapper personalizat pentru a mapa rezultatele din baza de date la obiecte de tip Medic
    private static class MedicRowMapper implements RowMapper<Medic> {
        @Override
        public Medic mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medic medic = new Medic();
            medic.setIdMedic(rs.getInt("ID_Medic")); //Seteaza ID-ul medicului
            medic.setNume(rs.getString("Nume")); //Seteaza numele medicului
            medic.setPrenume(rs.getString("Prenume")); //Seteaza prenumele medicului
            medic.setCnp(rs.getString("CNP")); //Seteaza CNP-ul medicului
            medic.setSex(rs.getString("Sex")); //Seteaza sexul medicului
            medic.setNumarTelefon(rs.getString("NumarTelefon")); //Seteaza numarul de telefon
            medic.setOras(rs.getString("Oras")); //Seteaza orasul medicului
            medic.setJudet(rs.getString("Judet")); //Seteaza judetul medicului
            medic.setIdSupervizor(rs.getInt("ID_Supervisor")); //Seteaza ID-ul supervizorului
            medic.setIdSpital(rs.getInt("ID_Spital")); //Seteaza ID-ul spitalului
            medic.setNumeSupervizor(rs.getString("numeSupervizor")); //Seteaza numele supervizorului
            medic.setNumeSpital(rs.getString("numeSpital")); //Seteaza numele spitalului
            return medic;
        }
    }
}
