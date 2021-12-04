package lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.date.GetReservationDate;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Component
@Transactional
public class OrmReservationRepositoryImpl implements ReservationRepository {

    @Autowired private SessionFactory sessionFactory;
    @Autowired private GetReservationDate getReservationDate;

    @Override
    public void addReservation(Reservation reservation) {
        sessionFactory.getCurrentSession().save(reservation);
    }

    @Override
    public boolean removeReservation(Long idReservation) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Reservation WHERE reservation_id = :reservation_id");
        query.setParameter("reservation_id", idReservation);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();
    }

    @Override
    public boolean editReservation(Long reservationID, EditReservationEnum userInput, String changes) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Reservation SET "
        + userInput.toString().toUpperCase(Locale.ROOT) + " = :changes WHERE reservation_id = :reservation_id");
        query.setParameter("reservation_id", reservationID);
        query.setParameter("changes", changes);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Reservation> findByReservationId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reservation r WHERE reservation_id = :reservation_id");
        query.setParameter("reservation_id", id);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findByReservationIdAndDate(Long id, LocalDateTime dateTime) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reservation r WHERE reservation_id = :reservation_id AND " +
                        "reservation_date = ' " + getReservationDate.getStringDate(dateTime) + "'");
        query.setParameter("reservation_id", id);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findByUserId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reservation r WHERE id_visitor= :id_visitor");
        query.setParameter("id_visitor", id);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findByTableId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reservation r WHERE id_table = :id_table");
        query.setParameter("id_table", id);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findByMenuId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reservation r WHERE id_menu = :id_menu");
        query.setParameter("id_menu", id);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findByDate(LocalDateTime date) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reservation r WHERE reservation_date = ' "
        + getReservationDate.getStringDate(date) + "'");
        return query.getResultList();
    }

    @Override
    public List<Reservation> findByUserIdAndTableId(Long clientId, Long tableId) {
        return null;
    }

    @Override
    public List<Reservation> findByUserIdAndMenuId(Long clientId, Long menuId) {
        return null;
    }

    @Override
    public List<Reservation> findByUserIdAndDate(Long clientId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByTableIdAndMenuId(Long tableId, Long menuId) {
        return null;
    }

    @Override
    public List<Reservation> findByTableIdAndDate(Long tableId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByMenuIdAndDate(Long menuId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByUserIdTableIdAndMenuId(Long clientId, Long tableId, Long menuId) {
        return null;
    }

    @Override
    public List<Reservation> findByUserIdMenuIdAndDate(Long clientId, Long menuId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByUserIdTableIdAndDate(Long clientId, Long tableId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByTableIdMenuIdAndDate(Long tableId, Long menuId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByUserIdTableIdMenuIdAndDate(Long clientId, Long tableId, Long menuId, LocalDateTime date) {
        return null;
    }
}
