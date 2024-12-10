package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class PatientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Patient> getAllPatients() {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, "+
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "INNER JOIN medici M ON P.id_medic = M.id_medic " +
                "INNER JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "ORDER BY P.ID_Pacient";
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }

    public List<Patient> findByNume(String nume) {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "INNER JOIN medici M ON P.id_medic = M.id_medic " +
                "INNER JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE P.nume LIKE CONCAT('%', ?, '%') OR P.prenume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY P.ID_Pacient";
        return jdbcTemplate.query(sql, new PatientRowMapper(), nume, nume);
    }

    public int savePatient(Patient pacient) {
        String sql = "INSERT INTO pacienti (Nume, Prenume, CNP, Sex, DataNasterii, " +
                "DataInrolareProgram, Strada, NumarTelefon, Oras, Judet, ID_Medic, " +
                "ID_Apartinator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

    public int updatePacient(Patient pacient) {
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

    public int insertPacient(Patient pacient) {
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

    public void addMedicamentForPacient(int id_pacient, int id_medicament, java.sql.Date datastarttratament) {
        String sql = "INSERT INTO pacienti_medicamente (id_pacient, id_medicament, datastarttratament) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id_pacient, id_medicament, datastarttratament);
    }

    public void addBoalaForPacient(int id_pacient, int id_boala, java.sql.Date dataaparitie) {
        String sql = "INSERT INTO pacienti_boli (id_pacient, id_boala, dataaparitie) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id_pacient, id_boala, dataaparitie);
    }

    public Patient findById(int id) {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "INNER JOIN medici M ON P.id_medic = M.id_medic " +
                "INNER JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE P.id_pacient = ?";
        return jdbcTemplate.queryForObject(sql, new PatientDAO.PatientRowMapper(), id);
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

    private static class PatientRowMapper implements RowMapper<Patient> {
        @Override
        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
            Patient patient = new Patient();
            patient.setId_pacient(rs.getInt("ID_Pacient"));
            patient.setNume(rs.getString("Nume"));
            patient.setPrenume(rs.getString("Prenume"));
            patient.setCnp(rs.getString("CNP"));
            patient.setSex(rs.getString("Sex"));
            patient.setDataNasterii(rs.getDate("DataNasterii"));
            patient.setDataInrolareProgram(rs.getDate("DataInrolareProgram"));
            patient.setStrada(rs.getString("Strada"));
            patient.setNumarTelefon(rs.getString("NumarTelefon"));
            patient.setOras(rs.getString("Oras"));
            patient.setJudet(rs.getString("Judet"));
            patient.setId_medic(rs.getInt("ID_Medic"));
            patient.setId_apartinator(rs.getInt("ID_Apartinator"));
            patient.setMedicNume(rs.getString("numeMedic"));
            patient.setApartinatorNume(rs.getString("NumeApartinator"));
            return patient;
        }
    }
}