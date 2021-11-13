package lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {

    void addReservation(Reservation reservation);

    boolean removeReservation(Long idReservation);

    List<Reservation> getAllReservations();

    boolean editReservation(Long reservationID, EditReservationEnum userInput, String changes);

    List<Reservation> findByReservationId(Long id);

    List<Reservation> findByClientId(Long id);

    List<Reservation> findByTableId(Long id);

    List<Reservation> findByMenuId(Long id);

    List<Reservation> findByDate(LocalDateTime date);

    List<Reservation> findByClientIdAndTableId(Long clientId, Long tableId);

    List<Reservation> findByClientIdAndMenuId(Long clientId, Long menuId);

    List<Reservation> findByClientIdAndDate(Long clientId, LocalDateTime date);

    List<Reservation> findByTableIdAndMenuId(Long tableId, Long menuId);

    List<Reservation> findByTableIdAndDate(Long tableId, LocalDateTime date);

    List<Reservation> findByMenuIdAndDate(Long menuId, LocalDateTime date);

    List<Reservation> findByClientIdTableIdAndMenuId(Long clientId, Long tableId, Long menuId);

    List<Reservation> findByClientIdMenuIdAndDate(Long clientId, Long menuId, LocalDateTime date);

    List<Reservation> findByClientIdTableIdAndDate(Long clientId, Long tableId, LocalDateTime date);

    List<Reservation> findByTableIdMenuIdAndDate(Long tableId, Long menuId, LocalDateTime date);

    List<Reservation> findByClientIdTableIdMenuIdAndDate(Long clientId, Long tableId, Long menuId, LocalDateTime date);

}
