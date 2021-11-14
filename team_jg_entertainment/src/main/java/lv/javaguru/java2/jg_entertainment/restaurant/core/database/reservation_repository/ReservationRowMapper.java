package lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReservationRowMapper implements RowMapper<Reservation> {

    @Autowired private VisitorsRepository databaseVisitors;
    @Autowired private MenuRepository databaseMenu;
    @Autowired private TableRepository databaseTable;

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(rs.getLong("reservation_id"));
        reservation.setVisitor(databaseVisitors.findClientById(rs.getLong("id_visitor")).get(0));
        reservation.setTable(databaseTable.findTableById(rs.getLong("id_table")).get(0));
        reservation.setMenu(databaseMenu.findById(rs.getLong("id_menu")).get(0));
        reservation.setReservationDate(rs.getTimestamp("reservation_date").toLocalDateTime());
        return reservation;
    }
}
