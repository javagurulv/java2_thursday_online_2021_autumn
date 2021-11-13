package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Component
public class JdbcReservationRepositoryImpl implements ReservationRepository {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private ReservationRowMapper reservationRowMapper;

    @Override
    public void addReservation(Reservation reservation) {
        jdbcTemplate.update(
                "INSERT INTO reservation (id_visitor, id_table, id_menu, reservation_date) "
                        + "VALUES (?, ?, ?, ?) ",
                reservation.getVisitor().getIdClient(),
                reservation.getTable().getId(),
                reservation.getMenu().getNumber(),
                reservation.getReservationDate());}
       /* String sql = "SELECT reservation_id FROM reservation WHERE id_visitor = ? AND id_table = ? AND id_menu = ? AND reservation_date = ?";
        Object[] args =
                new Object[]{reservation.getIdVisitor(),
                        reservation.getTable(),
                        reservation.getIdMenu(),
                        reservation.getReservationDate()};
        Long renewedIdCodeReservation = jdbcTemplate.queryForObject(sql, args, Long.class);
        reservation.setIdReservation(renewedIdCodeReservation);
    }*/

    @Override
    public boolean removeReservation(Long idReservation) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        Object[] args = new Object[]{idReservation};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql, new ReservationRowMapper());
    }

    @Override
    public boolean editReservation(Long reservationID, EditReservationEnum userInput, String changes) {
        String sql = "UPDATE reservation SET " + userInput.toString().toLowerCase(Locale.ROOT)
                + " = ? WHERE reservation_id = ?";
        return jdbcTemplate.update(sql, changes, reservationID) == 1;
    }

    @Override
    public List<Reservation> findByReservationId(Long id) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientId(Long id) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByTableId(Long id) {
        String sql = "SELECT * FROM reservation WHERE id_table = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByMenuId(Long id) {
        String sql = "SELECT * FROM reservation WHERE id_menu = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByDate(LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE reservation_date = ?";
        Object[] args = new Object[]{date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientIdAndTableId(Long clientId, Long tableId) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ? AND id_table = ?";
        Object[] args = new Object[]{clientId, tableId};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientIdAndMenuId(Long clientId, Long menuId) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ? AND id_menu = ?";
        Object[] args = new Object[]{clientId, menuId};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientIdAndDate(Long clientId, LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ? AND reservation_date = ?";
        Object[] args = new Object[]{clientId, date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByTableIdAndMenuId(Long tableId, Long menuId) {
        String sql = "SELECT * FROM reservation WHERE id_table = ? AND id_menu = ?";
        Object[] args = new Object[]{tableId, menuId};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByTableIdAndDate(Long tableId, LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE id_table = ? AND reservation_date = ?";
        Object[] args = new Object[]{tableId, date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByMenuIdAndDate(Long menuId, LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE id_menu = ? AND reservation_date = ?";
        Object[] args = new Object[]{menuId, date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientIdTableIdAndMenuId(Long clientId, Long tableId, Long menuId) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ? AND id_table = ? AND id_menu = ?";
        Object[] args = new Object[]{clientId, tableId, menuId};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientIdMenuIdAndDate(Long clientId, Long menuId, LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ? AND id_menu = ? AND reservation_date = ?";
        Object[] args = new Object[]{clientId, menuId, date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientIdTableIdAndDate(Long clientId, Long tableId, LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ? AND id_table = ? AND reservation_date = ?";
        Object[] args = new Object[]{clientId, tableId, date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByTableIdMenuIdAndDate(Long tableId, Long menuId, LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE id_table = ? AND id_menu = ? AND reservation_date = ?";
        Object[] args = new Object[]{tableId, menuId, date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }

    @Override
    public List<Reservation> findByClientIdTableIdMenuIdAndDate(Long clientId, Long tableId, Long menuId, LocalDateTime date) {
        String sql = "SELECT * FROM reservation WHERE id_visitor = ? AND id_table = ? AND id_menu = ? AND reservation_date = ?";
        Object[] args = new Object[]{clientId, tableId, menuId, date};
        return jdbcTemplate.query(sql, args, reservationRowMapper);
    }
}
