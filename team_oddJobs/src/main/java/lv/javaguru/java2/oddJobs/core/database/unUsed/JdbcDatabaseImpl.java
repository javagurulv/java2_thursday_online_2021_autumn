package lv.javaguru.java2.oddJobs.core.database.unUsed;

import lv.javaguru.java2.oddJobs.core.database.rowMapper.AdvertisementRowMapper;
import lv.javaguru.java2.oddJobs.core.database.rowMapper.ClientRowMapper;
import lv.javaguru.java2.oddJobs.core.database.rowMapper.SpecialistRowMapper;
import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addClient(Client client) {

        jdbcTemplate.update(
                "INSERT INTO Clients (clientName, clientSurname) "
                        + "VALUES (?, ?)",
                client.getClientName(), client.getClientSurname()
        );
    }

    @Override
    public boolean removeClient(Long clientId, String clientName, String clientSurname) {
        String sql = "DELETE FROM clients WHERE clientId = ? AND clientName = ? AND clientSurname = ? ";
        Object[] args = new Object[]{clientId, clientName, clientSurname};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean removeClientById(Long clientId) {

        String sql = "DELETE FROM clients WHERE clientId = ?";
        Object[] args = new Object[]{clientId};
        return jdbcTemplate.update(sql, args) == 1;
    }


    @Override
    public List<Client> findClientsById(Long clientId) {
        String sql = "SELECT * FROM clients WHERE clientId = ?";
        Object[] args = new Object[]{clientId};
        return jdbcTemplate.query(sql, args, new ClientRowMapper());
    }

    @Override
    public List<Client> findClientsByName(String clientName) {
        String sql = "SELECT * FROM clients WHERE clientName = ?";
        Object[] args = new Object[]{clientName};
        return jdbcTemplate.query(sql, args, new ClientRowMapper());
    }

    @Override
    public List<Client> findClientBySurname(String clientSurname) {
        String sql = "SELECT * FROM clients WHERE clientSurname = ?";
        Object[] args = new Object[]{clientSurname};
        return jdbcTemplate.query(sql, args, new ClientRowMapper());
    }

    @Override
    public List<Client> findClientByIdAndNameAndSurname(Long clientId, String clientName, String clientSurname) {
        String sql = "SELECT * FROM clients WHERE id = ? AND clientName = ? AND clientSurname = ? ";
        Object[] args = new Object[]{clientId, clientName, clientSurname};
        return jdbcTemplate.query(sql, args, new ClientRowMapper());
    }

    @Override
    public void addSpecialist(Specialist specialist) {

        jdbcTemplate.update(
                "INSERT INTO Specialists (specialistName, specialistSurname,specialistProfession) "
                        + "VALUES (?, ?,?)",
                specialist.getSpecialistName(), specialist.getSpecialistSurname(), specialist.getSpecialistProfession()
        );
    }

    @Override
    public boolean removeSpecialist(Long specialistId, String specialistName, String specialistSurname) {
        String sql = "DELETE FROM specialists WHERE specialistId = ? AND specialistName = ? AND specialistSurname = ?";
        Object[] args = new Object[]{specialistId, specialistName, specialistSurname};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean removeSpecialistById(Long specialistId) {
        String sql = "DELETE FROM specialists WHERE specialistId = ?";
        Object[] args = new Object[]{specialistId};
        return jdbcTemplate.update(sql, args) == 1;
    }


    @Override
    public List<Specialist> findSpecialistById(Long specialistId) {
        String sql = "SELECT * FROM specialists WHERE specialistId = ?";
        Object[] args = new Object[]{specialistId};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistByName(String specialistName) {
        String sql = "SELECT * FROM specialists WHERE specialistName = ?";
        Object[] args = new Object[]{specialistName};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistBySurname(String specialistSurname) {
        String sql = "SELECT * FROM specialists WHERE specialistSurname = ?";
        Object[] args = new Object[]{specialistSurname};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistByProfession(String specialistProfession) {
        String sql = "SELECT * FROM specialists WHERE profession = ?";
        Object[] args = new Object[]{specialistProfession};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession) {
        String sql = "SELECT * FROM specialists WHERE specialistName = ? AND specialistSurname = ? AND specialistProfession = ? ";
        Object[] args = new Object[]{specialistName, specialistSurname, specialistProfession};
        return jdbcTemplate.query(sql, args, new SpecialistRowMapper());
    }

    @Override
    public void addAdvertisement(Advertisement advBoard) {

        jdbcTemplate.update(
                "INSERT INTO Advertisements (advTitle, advDescription) "
                        + "VALUES (?, ?)",
                advBoard.getAdvTitle(), advBoard.getAdvDescription()
        );
    }

    @Override
    public boolean removeAdvertisement(Long advId, String advBoardTitle) {
        String sql = "DELETE FROM advertisements WHERE advId = ? AND advBoardTitle = ?";
        Object[] args = new Object[]{advId, advBoardTitle};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Advertisement> findAdvertisementByTitle(String advTitle) {
        String sql = "SELECT * FROM advertisements WHERE advTitle = ? ";
        Object[] args = new Object[]{advTitle};
        return jdbcTemplate.query(sql, args, new AdvertisementRowMapper());
    }

    @Override
    public List<Advertisement> findAdvertisementById(long advId) {
        String sql = "SELECT * FROM advertisements WHERE advId = ? ";
        Object[] args = new Object[]{advId};
        return jdbcTemplate.query(sql, args, new AdvertisementRowMapper());
    }

    @Override
    public List<Specialist> getAllSpecialist() {
        String sql = "SELECT * FROM specialists";
        return jdbcTemplate.query(sql, new SpecialistRowMapper());
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM clients";
        return jdbcTemplate.query(sql, new ClientRowMapper());
    }

    @Override
    public List<Advertisement> getAllAdvertisement() {
        String sql = "SELECT * FROM advertisements";
        return jdbcTemplate.query(sql, new AdvertisementRowMapper());
    }
}
