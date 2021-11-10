package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

@Component
public class ReservationRowMapper implements RowMapper<Reservation> {

    @Autowired    private DatabaseVisitorsImpl databaseVisitors;
    @Autowired    private DatabaseMenuImpl databaseMenu;
    @Autowired    private DatabaseTableImpl databaseTable;

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(rs.getLong("reservation_id"));
        reservation.setVisitor(databaseVisitors.findClientById(rs.getLong("id_visitor")).get(0));
        reservation.setTable(databaseTable.findTabletById(rs.getLong("id_table")).get(0));
        reservation.setMenu(databaseMenu.findById(rs.getLong("id_menu")).get(0));

        reservation.setReservationDate(rs.getTimestamp("reservation_date").toLocalDateTime());
        reservation.setSqlDate(rs.getTimestamp("date").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

        return reservation;
    }
}
