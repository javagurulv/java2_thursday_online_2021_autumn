package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(rs.getLong("reservation_id"));
        reservation.setIdVisitor(rs.getLong("id_visitor"));
        reservation.setIdTable(rs.getLong("id_table"));
        reservation.setIdMenu(rs.getLong("id_menu"));
        reservation.setReservationDate(rs.getDate("reservation_date"));
        return reservation;
    }
}
