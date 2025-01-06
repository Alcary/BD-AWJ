package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class PacientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pacient> getAllPatients() {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, "+
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "INNER JOIN medici M ON P.id_medic = M.id_medic " +
                "INNER JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "ORDER BY P.ID_Pacient";
        return jdbcTemplate.query(sql, new PacientRowMapper());
    }

    public List<Pacient> findByNume(String nume) {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "INNER JOIN medici M ON P.id_medic = M.id_medic " +
                "INNER JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE P.nume LIKE CONCAT('%', ?, '%') OR P.prenume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY P.ID_Pacient";
        return jdbcTemplate.query(sql, new PacientRowMapper(), nume, nume);
    }

    public List<Pacient> findPacientBoala(String boala) {
        String sql = "SELECT P.*, " +
                "CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "INNER JOIN medici M ON P.id_medic = M.id_medic " +
                "INNER JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE P.id_pacient IN ( " +
                "   SELECT PB.id_pacient " +
                "   FROM pacienti_boli PB " +
                "   INNER JOIN boli_asociate B ON PB.id_boala = B.id_boala " +
                "   WHERE B.nume LIKE CONCAT('%', ?, '%') " +
                ")";
        return jdbcTemplate.query(sql, new PacientRowMapper(), boala);
    }

    public int updatePacient(Pacient pacient) {
        String sql = "UPDATE pacienti SET Nume = ?, Prenume = ?, CNP = ?, " +
                "Sex = ?, DataNasterii = ?, DataInrolareProgram = ?, Strada = ?, " +
                "NumarTelefon = ?, Oras = ?, Judet = ?, id_medic = ?, id_apartinator = ? WHERE id_pacient = ?";
        return jdbcTemplate.update(sql,
                pacient.getNume(),
                pacient.getPrenume(),
                pacient.getCnp(),
                pacient.getSex(),
                pacient.getDataNasterii(),
                pacient.getDataInrolareProgram(),
                pacient.getStrada(),
                pacient.getNumarTelefon(),
                pacient.getOras(),
                pacient.getJudet(),
                pacient.getId_medic(),
                pacient.getId_apartinator(),
                pacient.getId_pacient());
    }

    public int insertPacient(Pacient pacient) {
        String sql = "INSERT INTO pacienti (Nume, Prenume, CNP, Sex, DataNasterii, DataInrolareProgram, Strada, NumarTelefon, Oras, Judet, id_medic, id_apartinator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                pacient.getNume(),
                pacient.getPrenume(),
                pacient.getCnp(),
                pacient.getSex(),
                pacient.getDataNasterii(),
                pacient.getDataInrolareProgram(),
                pacient.getStrada(),
                pacient.getNumarTelefon(),
                pacient.getOras(),
                pacient.getJudet(),
                pacient.getId_medic(),
                pacient.getId_apartinator());
    }

    public Pacient findById(int id) {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "INNER JOIN medici M ON P.id_medic = M.id_medic " +
                "INNER JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE P.id_pacient = ?";
        return jdbcTemplate.queryForObject(sql, new PacientDAO.PacientRowMapper(), id);
    }

    public void deletePacient(int id) {
        String sql = "DELETE FROM pacienti WHERE id_pacient = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Map<String, Object>> obtineDetaliiMedicale(int id) {
        String sql = "SELECT M.Nume AS NumeMedicament, PM.datastarttratament AS DataStartTratament, " +
                "PM.datafinalizaretratament AS DataFinalizareTratament, " +
                "PM.reactiiadverse AS ReactiiAdverse FROM pacienti P " +
                "LEFT JOIN pacienti_medicamente PM ON P.id_pacient = PM.id_pacient " +
                "LEFT JOIN medicamente M ON PM.id_medicament = M.id_medicament " +
                "WHERE P.id_pacient = ?";
        return jdbcTemplate.queryForList(sql, id);
    }

    public List<Map<String, Object>> obtineIstoricMedical(int id) {
        String sql = "SELECT B.Nume AS NumeBoala, " +
                "PB.dataaparitie AS DataAparitieiBolii " +
                "FROM pacienti P " +
                "LEFT JOIN pacienti_boli PB ON P.id_pacient = PB.id_pacient " +
                "LEFT JOIN boli_asociate B ON PB.id_boala = B.id_boala " +
                "WHERE P.id_pacient = ?";
        return jdbcTemplate.queryForList(sql, id);
    }

    private static class PacientRowMapper implements RowMapper<Pacient> {
        @Override
        public Pacient mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pacient pacient = new Pacient();
            pacient.setId_pacient(rs.getInt("ID_Pacient"));
            pacient.setNume(rs.getString("Nume"));
            pacient.setPrenume(rs.getString("Prenume"));
            pacient.setCnp(rs.getString("CNP"));
            pacient.setSex(rs.getString("Sex"));
            pacient.setDataNasterii(rs.getDate("DataNasterii"));
            pacient.setDataInrolareProgram(rs.getDate("DataInrolareProgram"));
            pacient.setStrada(rs.getString("Strada"));
            pacient.setNumarTelefon(rs.getString("NumarTelefon"));
            pacient.setOras(rs.getString("Oras"));
            pacient.setJudet(rs.getString("Judet"));
            pacient.setId_medic(rs.getInt("ID_Medic"));
            pacient.setId_apartinator(rs.getInt("ID_Apartinator"));
            pacient.setMedicNume(rs.getString("numeMedic"));
            pacient.setApartinatorNume(rs.getString("NumeApartinator"));
            return pacient;
        }
    }
}