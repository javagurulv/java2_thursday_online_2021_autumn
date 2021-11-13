package lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

//@Component
@Transactional
public class OrmReservationRepositoryImpl implements ReservationRepository {

    @Autowired private SessionFactory sessionFactory;

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
        return false;
    }

    @Override
    public List<Reservation> findByReservationId(Long id) {
        return null;
    }

    @Override
    public List<Reservation> findByClientId(Long id) {
        return null;
    }

    @Override
    public List<Reservation> findByTableId(Long id) {
        return null;
    }

    @Override
    public List<Reservation> findByMenuId(Long id) {
        return null;
    }

    @Override
    public List<Reservation> findByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByClientIdAndTableId(Long clientId, Long tableId) {
        return null;
    }

    @Override
    public List<Reservation> findByClientIdAndMenuId(Long clientId, Long menuId) {
        return null;
    }

    @Override
    public List<Reservation> findByClientIdAndDate(Long clientId, LocalDateTime date) {
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
    public List<Reservation> findByClientIdTableIdAndMenuId(Long clientId, Long tableId, Long menuId) {
        return null;
    }

    @Override
    public List<Reservation> findByClientIdMenuIdAndDate(Long clientId, Long menuId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByClientIdTableIdAndDate(Long clientId, Long tableId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByTableIdMenuIdAndDate(Long tableId, Long menuId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Reservation> findByClientIdTableIdMenuIdAndDate(Long clientId, Long tableId, Long menuId, LocalDateTime date) {
        return null;
    }
}
