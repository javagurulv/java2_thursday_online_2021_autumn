package lv.javaguru.java2.oddJobs.database.rowMapper;

import lv.javaguru.java2.oddJobs.domain.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

        Client client = new Client();
        client.setClientId(rs.getLong("id"));
        client.setClientName(rs.getString("clientName"));
        client.setClientSurname(rs.getString("clientSurname"));
        return client;
    }
}
