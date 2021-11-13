package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationEnum;
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
    @Autowired
    private VisitorsRepository visitorsRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void addReservation(Reservation reservation) {
        reservation.setIdReservation(nextId);
        nextId++;
        reservationList.add(reservation);
    }

    /*
        @Override
        public boolean removeReservation(Long idReservation) {
            boolean deleteReservationInformation = false;

            Optional<Reservation> optionalReservation = reservationList.stream()
                    .filter(reservation -> reservation.getTelephoneNumber().equals(idReservation))
                    .findFirst();
            if (optionalReservation.isPresent()) {
                Reservation reservation = optionalReservation.get();
                deleteReservationInformation = reservationList.remove(reservation);
            }
            return deleteReservationInformation;
        }
         */
    @Override
    public boolean removeReservation(Long idReservation) {
        return reservationList.removeIf(p -> p.getIdReservation().equals(idReservation));
    }


    @Override
    public List<Reservation> getAllReservations() {
        return reservationList;
    }

    @Override
    public boolean editReservation(Long reservationID, EditReservationEnum userInput, String changes) {
        boolean isReservationEdited = false;
        Optional<Reservation> reservationToEditOpt = reservationList.stream()
                .filter(clientReservation -> Objects.equals(clientReservation.getIdReservation(), reservationID)).findFirst();
        if (reservationToEditOpt.isPresent()) {
            Reservation reservationToEdit = reservationToEditOpt.get();
            if (userInput.equals(EditReservationEnum.TABLE_ID)) {
                reservationToEdit.setTable(tableRepository.findTableById(Long.parseLong(changes)).get(0));
                isReservationEdited = true;
            } else if (userInput.equals(EditReservationEnum.CLIENT_ID)) {
                reservationToEdit.setVisitor(visitorsRepository.findClientById(Long.parseLong(changes)).get(0));
                isReservationEdited = true;
            } else if (userInput.equals(EditReservationEnum.MENU_ID)) {
                reservationToEdit.setMenu(menuRepository.findById(Long.parseLong(changes)).get(0));
                isReservationEdited = true;
            } else if (userInput.equals(EditReservationEnum.DATE)) {
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
    public List<Reservation> findByClientId(Long id) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), id))
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
    public List<Reservation> findByClientIdAndTableId(Long clientId, Long tableId) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), clientId))
                .filter(reservation -> Objects.equals(reservation.getTable().getId(), tableId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByClientIdAndMenuId(Long clientId, Long menuId) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), clientId))
                .filter(reservation -> Objects.equals(reservation.getMenu().getNumber(), menuId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByClientIdAndDate(Long clientId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), clientId))
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByTableIdAndMenuId(Long tableId, Long menuId) {
            return reservationList.stream()
                    .filter(reservation -> Objects.equals(reservation.getTable().getId(), tableId))
                    .filter(reservation -> Objects.equals(reservation.getMenu().getNumber(), menuId))
                    .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByTableIdAndDate(Long tableId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getTable().getId(), tableId))
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByMenuIdAndDate(Long menuId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getMenu().getNumber(), menuId))
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByClientIdTableIdAndMenuId(Long clientId, Long tableId, Long menuId) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), clientId))
                .filter(reservation -> Objects.equals(reservation.getTable().getId(), tableId))
                .filter(reservation -> Objects.equals(reservation.getMenu().getNumber(), menuId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByClientIdMenuIdAndDate(Long clientId, Long menuId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), clientId))
                .filter(reservation -> Objects.equals(reservation.getMenu().getNumber(), menuId))
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByClientIdTableIdAndDate(Long clientId, Long tableId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), clientId))
                .filter(reservation -> Objects.equals(reservation.getTable().getId(), tableId))
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByTableIdMenuIdAndDate(Long tableId, Long menuId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getTable().getId(), tableId))
                .filter(reservation -> Objects.equals(reservation.getMenu().getNumber(), menuId))
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByClientIdTableIdMenuIdAndDate(Long clientId, Long tableId, Long menuId, LocalDateTime date) {
        return reservationList.stream()
                .filter(reservation -> Objects.equals(reservation.getVisitor().getIdClient(), clientId))
                .filter(reservation -> Objects.equals(reservation.getTable().getId(), tableId))
                .filter(reservation -> Objects.equals(reservation.getMenu().getNumber(), menuId))
                .filter(reservation -> Objects.equals(reservation.getReservationDate(), date))
                .collect(Collectors.toList());
    }
}
