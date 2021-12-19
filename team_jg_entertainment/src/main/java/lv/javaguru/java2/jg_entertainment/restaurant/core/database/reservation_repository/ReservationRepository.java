package lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.UpdateReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {

    void addReservation(Reservation reservation);

    boolean removeReservation(Long idReservation);

    List<Reservation> getAllReservations();

    boolean editReservation(Long reservationID, UpdateReservationEnum userInput, String changes);

    List<Reservation> findByReservationId(Long id);

    List<Reservation> findByReservationIdAndDate(Long id, LocalDateTime dateTime);

    List<Reservation> findByUserId(Long id);

    List<Reservation> findByTableId(Long id);

    List<Reservation> findByMenuId(Long id);

    List<Reservation> findByDate(LocalDateTime date);

    List<Reservation> findByUserIdAndDate(Long clientId, LocalDateTime date);
}
