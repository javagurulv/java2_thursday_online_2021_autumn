package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.AddReservationValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Transactional
public class AddReservationService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private TableRepository tableRepository;
    @Autowired private ReservationRepository reservationRepository;
    @Autowired private AddReservationValidator validator;

    public AddReservationResponse execute(AddReservationRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddReservationResponse(errors);
        }
        User user = usersRepository.findUserById(Long.valueOf(request.getUserID())).get(0);
        Menu menu = menuRepository.findById(Long.valueOf(request.getMenuID())).get(0);
        Table table = tableRepository.findTableById(Long.valueOf(request.getTableID())).get(0);
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .parse(request.getReservationDate()));
        Reservation reservation = new Reservation(user, menu, table, date);
        reservationRepository.addReservation(reservation);
        return new AddReservationResponse(reservation);
    }
}
