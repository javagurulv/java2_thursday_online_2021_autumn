package lv.javaguru.java2.oddJobs.database;

import lv.javaguru.java2.oddJobs.database.rowMapper.ClientRowMapper;
import lv.javaguru.java2.oddJobs.database.rowMapper.SpecialistRowMapper;
import lv.javaguru.java2.oddJobs.domain.Advertisement;
import lv.javaguru.java2.oddJobs.domain.Client;
import lv.javaguru.java2.oddJobs.domain.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addClient(Client client) {

        jdbcTemplate.update(
                "INSERT INTO Client (clientName, clientSurname) "
                        + "VALUES (?, ?)",
                client.getClientName(), client.getClientSurname()
        );
    }

    @Override
    public void addSpecialist(Specialist specialist) {

        jdbcTemplate.update(
                "INSERT INTO Client (specialistName, specialistSurname,) "
                        + "VALUES (?, ?,?)",
                specialist.getSpecialistName(), specialist.getSpecialistSurname(), specialist.getSpecialistProfession()
        );
    }


    @Override
    public boolean removeClientById(Long clientId) {

        String sql = "DELETE FROM clients WHERE id = ?";
        Object[] args = new Object[]{clientId};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean removeSpecialistById(Long specialistId) {
        String sql = "DELETE FROM specialists WHERE id = ?";
        Object[] args = new Object[]{specialistId};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean removeClient(Long id, String name, String surName) {
        return false;
    }

    @Override
    public boolean removeSpecialist(Long id, String name, String surName) {
        return false;
    }

    @Override
    public List<Client> findClientsById(Long clientId) {
        return null;
    }

    @Override
    public List<Client> findClientsByName(String clientName) {
        return null;
    }

    @Override
    public List<Client> findClientBySurname(String clientSurname) {
        return null;
    }

    @Override
    public List<Client> findClientByIdAndNameAndSurname(Long id, String clientName, String clientSurname) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistById(Long specialistId) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistByName(String specialistName) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistBySurname(String specialistSurname) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistByProfession(String profession) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession) {
        return null;
    }

    @Override
    public void addAdvertisement(Advertisement advBoard) {

    }

    @Override
    public boolean removeAdvertisement(Long advId, String advBoardTitle) {
        return false;
    }

    @Override
    public List<Advertisement> findAdvertisementByTitle(String advTitle) {
        return null;
    }

    @Override
    public List<Advertisement> findAdvertisementById(long advId) {
        return null;
    }

    @Override
    public List<Specialist> getAllSpecialist() {
        String sql = "SELECT * FROM clients";
        return jdbcTemplate.query(sql, new SpecialistRowMapper());
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM clients";
        return jdbcTemplate.query(sql, new ClientRowMapper());
    }

    @Override
    public List<Advertisement> getAllAdvertisement() {
        return null;
    }
}
