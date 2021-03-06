package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.DateValidatorExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddReservationValidator {

    @Autowired private UsersRepository userDatabase;
    @Autowired private MenuRepository menuDatabase;
    @Autowired private TableRepository tableDatabase;
    @Autowired private DateValidatorExecution dateValidator;
    @Autowired private ReservationLongNumChecker longNumChecker;

    public List<CoreError> validate(AddReservationRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUserID(request).ifPresent(errors::add);
        validateUserIDParse(request).ifPresent(errors::add);
        validateTableID(request).ifPresent(errors::add);
        validateTableIDParse(request).ifPresent(errors::add);
        validateMenuID(request).ifPresent(errors::add);
        validateMenuIDParse(request).ifPresent(errors::add);
        validateReservationDate(request).ifPresent(errors::add);
        errors.addAll(validDateReservation(request));

        validateUsersIfPresent(request).ifPresent(errors::add);
        validateMenuIfPresent(request).ifPresent(errors::add);
        validateTableIfPresent(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUserID(AddReservationRequest request) {
        return (request.getUserID() == null || request.getUserID().isEmpty())
                ? Optional.of(new CoreError("User ID", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateUserIDParse(AddReservationRequest request) {
        return (request.getUserID() == null || request.getUserID().isEmpty())
                ? Optional.empty()
                : longNumChecker.validate(request.getUserID(), "User ID");
    }

    private Optional<CoreError> validateTableID(AddReservationRequest request) {
        return (request.getTableID() == null || request.getTableID().isEmpty())
                ? Optional.of(new CoreError("Table ID", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTableIDParse(AddReservationRequest request) {
        return (request.getTableID() == null || request.getTableID().isEmpty())
                ? Optional.empty()
                : longNumChecker.validate(request.getTableID(), "Table ID");
    }

    private Optional<CoreError> validateMenuID(AddReservationRequest request) {
        return (request.getMenuID() == null || request.getMenuID().isEmpty())
                ? Optional.of(new CoreError("Menu ID", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMenuIDParse(AddReservationRequest request) {
        return (request.getMenuID() == null || request.getMenuID().isEmpty())
                ? Optional.empty()
                : longNumChecker.validate(request.getMenuID(), "Menu ID");
    }

    private Optional<CoreError> validateReservationDate(AddReservationRequest request) {
        return (request.getReservationDate() == null || request.getReservationDate().isEmpty())
                ? Optional.of(new CoreError("date", "Must not be empty!"))
                : Optional.empty();
    }

    private List<CoreError> validDateReservation(AddReservationRequest request) {
        return request.getReservationDate() == null || request.getReservationDate().isEmpty()
                ? new ArrayList<>()
                : dateValidator.validate(request.getReservationDate());
    }

    private Optional<CoreError> validateUsersIfPresent(AddReservationRequest request) {
        return request.getUserID() == null || request.getUserID().isEmpty()
                ? Optional.empty()
                : userDatabase.findUserById(Long.valueOf(request.getUserID())).isEmpty()
                ? Optional.of(new CoreError("info about user", "was not found"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMenuIfPresent(AddReservationRequest request) {
        return request.getMenuID() == null || request.getMenuID().isEmpty()
                ? Optional.empty()
                : menuDatabase.findById(Long.valueOf(request.getMenuID())).isEmpty()
                ? Optional.of(new CoreError("info about menu", "was not found"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTableIfPresent(AddReservationRequest request) {
        return request.getTableID() == null || request.getTableID().isEmpty()
                ? Optional.empty()
                : tableDatabase.findTableById(Long.valueOf(request.getTableID())).isEmpty()
                ? Optional.of(new CoreError("info about table", "was not found"))
                : Optional.empty();
    }
}
