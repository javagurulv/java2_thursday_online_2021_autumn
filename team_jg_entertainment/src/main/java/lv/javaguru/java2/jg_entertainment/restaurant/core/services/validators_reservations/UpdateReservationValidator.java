package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.UpdateReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.DateValidatorExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class UpdateReservationValidator {

    @Autowired private ExistReservationValidator validator;
    @Autowired private EnumValidator enumValidator;
    @Autowired private DateValidatorExecution dateValidatorExecution;
    @Autowired private ReservationLongNumChecker reservationLongNumChecker;
    @Autowired private UsersRepository repository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private TableRepository tableRepository;

    public List<CoreError> validate(UpdateReservationRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateReservationID(request).ifPresent(errors::add);
        validateChangesId(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        validateEnumReservation(request).ifPresent(errors::add);
        errors.addAll(validateDate(request));
        if(errors.isEmpty()){
            validateReservationIs(request).ifPresent(errors::add);
            validateUserIs(request).ifPresent(errors::add);
            validateMenuIs(request).ifPresent(errors::add);
            validateTableIs(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateId(UpdateReservationRequest request){
        return request.getReservationId() == null || request.getReservationId().isEmpty()
                ? Optional.of(new CoreError("idReservation", "must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReservationID(UpdateReservationRequest request){
        return request.getReservationId() == null || request.getReservationId().isEmpty()
                ? Optional.empty()
                : reservationLongNumChecker.validate(request.getReservationId(), "id");
    }

    private Optional<CoreError> validateChangesId(UpdateReservationRequest request){
        return request.getEnumEditReservation() == null || request.getEnumEditReservation().isEmpty()
                ? Optional.empty()
                : request.getEnumEditReservation().toUpperCase(Locale.ROOT).equals("ID_VISITOR")
                || request.getEnumEditReservation().toUpperCase(Locale.ROOT).equals("ID_TABLE")
                || request.getEnumEditReservation().toUpperCase(Locale.ROOT).equals("ID_MENU")
                ? reservationLongNumChecker.validate(request.getChanges(), "id was changed")
                : Optional.empty();
    }

    private Optional<CoreError> validateReservationIs(UpdateReservationRequest request){
        return request.getReservationId() == null || request.getReservationId().isEmpty()
                ? Optional.empty()
                : validator.validate(Long.valueOf(request.getReservationId()));
    }

    private Optional<CoreError> validateUserIs(UpdateReservationRequest request){
        return request.getEnumEditReservation() == null || request.getEnumEditReservation().isEmpty()
                ? Optional.empty()
                : !request.getEnumEditReservation().toUpperCase(Locale.ROOT).equals("ID_VISITOR")
                ? Optional.empty()
                : repository.findUserById(Long.parseLong(request.getChanges())).isEmpty()
                ? Optional.of(new CoreError("user", "was not found"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMenuIs(UpdateReservationRequest request){
        return request.getEnumEditReservation() == null || request.getEnumEditReservation().isEmpty()
                ? Optional.empty()
                : !request.getEnumEditReservation().toUpperCase(Locale.ROOT).equals("ID_MENU")
                ? Optional.empty()
                : menuRepository.findById(Long.parseLong(request.getChanges())).isEmpty()
                ? Optional.of(new CoreError("menu", "was not found"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTableIs(UpdateReservationRequest request){
        return request.getEnumEditReservation() == null || request.getEnumEditReservation().isEmpty()
                ? Optional.empty()
                : !request.getEnumEditReservation().toUpperCase(Locale.ROOT).equals("ID_TABLE")
                ? Optional.empty()
                : tableRepository.findTableById(Long.parseLong(request.getChanges())).isEmpty()
                ? Optional.of(new CoreError("table", "was not found"))
                : Optional.empty();
    }

    private Optional<CoreError> validateChanges(UpdateReservationRequest request){
        return request.getChanges() == null || request.getChanges().isEmpty()
                ? Optional.of(new CoreError("changes", "must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEnumReservation(UpdateReservationRequest request){
        return request.getEnumEditReservation() == null || request.getEnumEditReservation().isEmpty()
                ? Optional.of(new CoreError("edit", "must not be empty"))
                : enumValidator.enumValidate(request.getEnumEditReservation());
    }

    private List<CoreError> validateDate(UpdateReservationRequest request){
        return request.getChanges() == null || request.getChanges().isEmpty()
                ? new ArrayList<>()
                : request.getEnumEditReservation().equals("RESERVATION_DATE")
                ? dateValidatorExecution.validate(request.getChanges())
                : new ArrayList<>();
    }
}
