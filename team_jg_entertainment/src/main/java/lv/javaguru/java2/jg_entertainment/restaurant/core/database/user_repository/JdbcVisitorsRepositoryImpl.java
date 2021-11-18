package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JdbcVisitorsRepositoryImpl implements VisitorsRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void saveClientToRestaurantList(Visitor clientInfo) {
        jdbcTemplate.update(
                "INSERT INTO visitors (visitor_id, visitor_name, visitor_surname, visitor_telephone_number) "
                        + "VALUES (?, ?, ?, ?) ",
                clientInfo.getIdClient(),
                clientInfo.getClientName(),
                clientInfo.getSurname(),
                clientInfo.getTelephoneNumber());
        String sql = "SELECT visitor_id FROM visitors WHERE visitor_name = ? AND visitor_surname = ? AND visitor_telephone_number = ?";
        Object[] args = new Object[]{clientInfo.getClientName(), clientInfo.getSurname(), clientInfo.getTelephoneNumber()};
        Long renewedIdCodeVisitor = jdbcTemplate.queryForObject(sql, args, Long.class);
        clientInfo.setIdClient(renewedIdCodeVisitor);
    }

    @Override
    public List<Visitor> findVisitorsByNameAndTelephoneNumber(String nameVisitors, String telephoneNumber) {
        String sql = "SELECT * FROM visitors WHERE visitor_name = ? AND visitor_telephone_number = ?";
        Object[] args = new Object[]{nameVisitors, telephoneNumber};
        return jdbcTemplate.query(sql, args, new VisitorRowMapper());
    }

    @Override
    public List<Visitor> findClientById(Long idVisitors) {
        String sql = "SELECT * FROM visitors WHERE visitor_id = ?";
        Object[] args = new Object[]{idVisitors};
        return jdbcTemplate.query(sql, args, new VisitorRowMapper());
    }

    @Override
    public List<Visitor> findByNameVisitor(String nameVisitor) {
        String sql = "SELECT * FROM visitors WHERE visitor_name = ?";
        Object[] args = new Object[]{nameVisitor};
        return jdbcTemplate.query(sql, args, new VisitorRowMapper());
    }

    @Override
    public List<Visitor> findBySurnameVisitor(String surnameVisitor) {
        String sql = "SELECT * FROM visitors WHERE visitor_surname = ?";
        Object[] args = new Object[]{surnameVisitor};
        return jdbcTemplate.query(sql, args, new VisitorRowMapper());
    }

    @Override
    public List<Visitor> findByNameAndSurname(String nameVisitor, String surnameVisitor) {
        String sql = "SELECT * FROM visitors WHERE visitor_name = ? AND visitor_surname = ?";
        Object[] args = new Object[]{nameVisitor, surnameVisitor};
        return jdbcTemplate.query(sql, args, new VisitorRowMapper());
    }

    @Override
    public boolean deleteClientWithIDAndName(Long id, String nameVisitors) {
        String sql = "DELETE FROM visitors WHERE visitor_id = ? AND visitor_name = ? ";
        Object[] args = new Object[]{id, nameVisitors};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Visitor> showAllClientsInList() {
        String sql = "SELECT * FROM visitors";
        return jdbcTemplate.query(sql, new VisitorRowMapper());
    }
}
