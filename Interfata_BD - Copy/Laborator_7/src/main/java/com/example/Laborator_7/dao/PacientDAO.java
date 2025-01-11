/** Realizeaza interogarile SQL pentru Pacienti
 * @author Calaras Alexandru
 * @version 4 Ianuarie 2025
 */
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

    // Returneaza toti pacientii, inclusiv numele medicului si apartinatorului asociat
    public List<Pacient> getAllPatients() {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS numeApartinator " +
                "FROM pacienti P " +
                "LEFT JOIN medici M ON P.id_medic = M.id_medic " +
                "LEFT JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "ORDER BY P.ID_Pacient";
        return jdbcTemplate.query(sql, new PacientRowMapper());
    }

    // Cauta pacienti dupa nume sau prenume
    public List<Pacient> findByNume(String nume) {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "LEFT JOIN medici M ON P.id_medic = M.id_medic " +
                "LEFT JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE P.nume LIKE CONCAT('%', ?, '%') OR P.prenume LIKE CONCAT('%', ?, '%') " +
                "ORDER BY P.ID_Pacient";
        return jdbcTemplate.query(sql, new PacientRowMapper(), nume, nume);
    }

    // Cauta pacienti care au o boala specifica
    public List<Pacient> findPacientBoala(String boala) {
        String sql = "SELECT P.*, " +
                "CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "LEFT JOIN medici M ON P.id_medic = M.id_medic " +
                "LEFT JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE EXISTS ( " +
                "   SELECT 1 " +
                "   FROM pacienti_boli PB " +
                "   INNER JOIN boli_asociate B ON PB.id_boala = B.id_boala " +
                "   WHERE PB.id_pacient = P.id_pacient " +
                "     AND B.nume LIKE CONCAT('%', ?, '%') " +
                ")";
        return jdbcTemplate.query(sql, new PacientRowMapper(), boala);
    }

    // Actualizeaza datele unui pacient
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

    // Insereaza un nou pacient in baza de date
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

    // Gaseste un pacient dupa ID-ul sau unic
    public Pacient findById(int id) {
        String sql = "SELECT P.*, CONCAT(M.Nume, ' ', M.Prenume) AS numeMedic, " +
                "CONCAT(A.Nume, ' ', A.Prenume) AS NumeApartinator " +
                "FROM pacienti P " +
                "LEFT JOIN medici M ON P.id_medic = M.id_medic " +
                "LEFT JOIN apartinatori A ON P.id_apartinator = A.id_apartinator " +
                "WHERE P.id_pacient = ?";
        return jdbcTemplate.queryForObject(sql, new PacientRowMapper(), id);
    }

    // Sterge un pacient din baza de date folosind ID-ul sau
    public void deletePacient(int id) {
        String sql = "DELETE FROM pacienti WHERE id_pacient = ?";
        jdbcTemplate.update(sql, id);
    }

    // Obtine detalii medicale despre tratamentele unui pacient
    public List<Map<String, Object>> obtineDetaliiMedicale(int id) {
        String sql = "SELECT M.Nume AS NumeMedicament, PM.datastarttratament AS DataStartTratament, " +
                "PM.datafinalizaretratament AS DataFinalizareTratament, " +
                "PM.reactiiadverse AS ReactiiAdverse FROM pacienti P " +
                "LEFT JOIN pacienti_medicamente PM ON P.id_pacient = PM.id_pacient " +
                "LEFT JOIN medicamente M ON PM.id_medicament = M.id_medicament " +
                "WHERE P.id_pacient = ?";
        return jdbcTemplate.queryForList(sql, id);
    }

    // Obtine istoricul medical al unui pacient
    public List<Map<String, Object>> obtineIstoricMedical(int id) {
        String sql = "SELECT B.Nume AS NumeBoala, " +
                "PB.dataaparitie AS DataAparitieiBolii " +
                "FROM pacienti P " +
                "LEFT JOIN pacienti_boli PB ON P.id_pacient = PB.id_pacient " +
                "LEFT JOIN boli_asociate B ON PB.id_boala = B.id_boala " +
                "WHERE P.id_pacient = ?";
        return jdbcTemplate.queryForList(sql, id);
    }

    // Returneaza cel mai mare ID al unui medic
    public int findLastMedicId() {
        String sql = "SELECT MAX(ID_medic) FROM medici";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Returneaza cel mai mare ID al unui apartinator
    public int findLastApartinator() {
        String sql = "SELECT MAX(id_apartinator) FROM apartinatori";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // RowMapper pentru maparea datelor din baza de date la obiecte Pacient
    private static class PacientRowMapper implements RowMapper<Pacient> {
        @Override
        public Pacient mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pacient pacient = new Pacient();
            pacient.setId_pacient(rs.getInt("ID_Pacient")); // Seteaza ID-ul pacientului
            pacient.setNume(rs.getString("Nume")); // Seteaza numele
            pacient.setPrenume(rs.getString("Prenume")); // Seteaza prenumele
            pacient.setCnp(rs.getString("CNP")); // Seteaza CNP-ul
            pacient.setSex(rs.getString("Sex")); // Seteaza sexul
            pacient.setDataNasterii(rs.getDate("DataNasterii")); // Seteaza data nasterii
            pacient.setDataInrolareProgram(rs.getDate("DataInrolareProgram")); // Seteaza data inrolarii in program
            pacient.setStrada(rs.getString("Strada")); // Seteaza strada
            pacient.setNumarTelefon(rs.getString("NumarTelefon")); // Seteaza numarul de telefon
            pacient.setOras(rs.getString("Oras")); // Seteaza orasul
            pacient.setJudet(rs.getString("Judet")); // Seteaza judetul
            pacient.setId_medic(rs.getInt("ID_Medic")); // Seteaza ID-ul medicului
            pacient.setId_apartinator(rs.getInt("ID_Apartinator")); // Seteaza ID-ul apartinatorului
            pacient.setMedicNume(rs.getString("numeMedic")); // Seteaza numele medicului
            pacient.setApartinatorNume(rs.getString("NumeApartinator")); // Seteaza numele apartinatorului
            return pacient;
        }
    }
}
