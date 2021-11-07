package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcReservationDatabaseImpl implements DatabaseReservation {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void addReservation(Reservation reservation) {
        jdbcTemplate.update(
                "INSERT INTO reservation (id_visitor, id_table, id_menu, reservation_date) "
                        + "VALUES (?, ?, ?, ?) ",
                reservation.getIdVisitor(),
                reservation.getIdTable(),
                reservation.getIdMenu(),
                reservation.getReservationDate());
        String sql = "SELECT reservation_id FROM reservation WHERE id_visitor = ? AND id_table = ? AND id_menu = ? AND reservation_date = ?";
        Object[] args =
                new Object[]{reservation.getIdVisitor(),
                        reservation.getTable(),
                        reservation.getIdMenu(),
                        reservation.getReservationDate()};
        Long renewedIdCodeReservation = jdbcTemplate.queryForObject(sql, args, Long.class);
        reservation.setIdReservation(renewedIdCodeReservation);
    }

    @Override
    public boolean removeReservation(Long idReservation) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        Object[] args = new Object[]{idReservation};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Reservation> showReservationList() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql, new ReservationRowMapper());
    }
}
