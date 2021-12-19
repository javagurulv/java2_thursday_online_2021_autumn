package lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.UpdateReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    private Long nextId = 1L;
    private final List<Reservation> reservationList = new ArrayList<>();
    @Autowired private UsersRepository usersRepository;
    @Autowired private TableRepository tableRepository;
    @Autowired private MenuRepository menuRepository;

    @Override
    public void addReservation(Reservation reservation) {
        reservation.setIdReservation(nextId);
        nextId++;
        reservationList.add(reservation);
    }

    @Override
    public boolean removeReservation(Long idReservation) {
        return reservationList.removeIf(p -> p.getIdReservation().equals(idReservation));
    }


    @Override
    public List<Reservation> getAllReservations() {
        return reservationList;
    }

    @Override
    public boolean editReservation(Long reservationID, UpdateReservationEnum userInput, String changes) {
        boolean isReservationEdited = false;
        Optional<Reservation> reservationToEditOpt = reservationList.stream()
                .filter(clientReservation -> Objects.equals(clientReservation.getIdReservation(), reservationID)).findFirst();
        if (reservationToEditOpt.isPresent()) {
            Reservation reservationToEdit = reservationToEditOpt.get();
            if (userInput.equals(UpdateReservationEnum.ID_TABLE)) {
                reservationToEdit.setTable(tableRepository.findTableById(Long.parseLong(changes)).get(0));
                isReservationEdited = true;
            } else if (userInput.equals(UpdateReservationEnum.ID_VISITOR)) {
                reservationToEdit.setUser(usersRepository.findUserById(Long.parseLong(changes)).get(0));
                isReservationEdited = true;
            } else if (userInput.equals(UpdateReservationEnum.ID_MENU)) {
                reservationToEdit.setMenu(menuRepository.findById(Long.parseLong(changes)).get(0));
                isReservationEdited = true;
            } else if (userInput.equals(UpdateReservationEnum.RESERVATION_DATE)) {
                reservationToEdit.setReservationDate(LocalDateTime.parse(changes));
                isReservationEdited = true;
            }
        }
        return isReservationEdited;
    }

    @Override
    public List<Reservation> findByReservationId(Long id) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getIdReservation(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByReservationIdAndDate(Long id, LocalDateTime dateTime) {
        return null;
    }

    @Override
    public List<Reservation> findByUserId(Long id) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getUser().getUserId(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByTableId(Long id) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getTable().getId(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByMenuId(Long id) {
        return reservationList.stream()
                .filter( reservation -> Objects.equals(reservation.getMenu().getNumber(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByDate(LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByUserIdAndDate(Long userId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getUser().getUserId(), userId))
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }
}
