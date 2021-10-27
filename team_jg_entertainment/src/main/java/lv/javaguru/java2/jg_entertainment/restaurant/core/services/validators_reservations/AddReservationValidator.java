package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenuImpl;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseTableImpl;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitorsImpl;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddReservationValidator {

    @Autowired
    private DatabaseVisitorsImpl visitorDatabase;
    @Autowired
    private DatabaseMenuImpl menuDatabase;
    @Autowired
    private DatabaseTableImpl tableDatabase;

    public List<CoreError> validate(AddReservationRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateVisitor(request).ifPresent(errors::add);
        validateTelephoneEmpty(request).ifPresent(errors::add);//
        validateTelephoneLength(request).ifPresent(errors::add);
        validateMenuTitle(request).ifPresent(errors::add);
        validateTableTitle(request).ifPresent(errors::add);
        validateReservationDate(request).ifPresent(errors::add);
        validateVisitorsIfPresent(request).ifPresent(errors::add);
        validateMenuIfPresent(request).ifPresent(errors::add);
        validateTableIfPresent(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateVisitor(AddReservationRequest request) {//visitor
        return (request.getVisitorName() == null
                || request.getVisitorName().isEmpty())
                ? Optional.of(new CoreError("visitorName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTelephoneEmpty(AddReservationRequest request) {//visitor
        return (request.getTelephoneNumber() == null
                || request.getTelephoneNumber().isEmpty())
                ? Optional.of(new CoreError("telephoneNumber", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTelephoneLength(AddReservationRequest request) {//visitor
        return (request.getTelephoneNumber().length() < 3
                || request.getTelephoneNumber().length() > 15)
                ? Optional.of(new CoreError("telephoneNumber", "length must have figures from 3 to 15!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMenuTitle(AddReservationRequest request) {
        return (request.getMenuTitle() == null
                || request.getMenuTitle().isEmpty())
                ? Optional.of(new CoreError("menuTitle", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTableTitle(AddReservationRequest request) {
        return (request.getTableTitle() == null
                || request.getTableTitle().isEmpty())
                ? Optional.of(new CoreError("tableTitle", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReservationDate(AddReservationRequest request) {
        return (request.getReservationDate() == null
                || request.getReservationDate().isEmpty())
                ? Optional.of(new CoreError("date", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateVisitorsIfPresent(AddReservationRequest request) {
        List<Visitors> visitors =
                visitorDatabase.findVisitorsByNameAndTelephoneNumber(request.getVisitorName(), request.getTelephoneNumber());
        return visitors.isEmpty()
                ? Optional.of(new CoreError("info about visitor", "was not found"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMenuIfPresent(AddReservationRequest request) {
        List<Menu> menu = menuDatabase.findByTitle(request.getMenuTitle());
        return menu.isEmpty()
                ? Optional.of(new CoreError("info about menu", "was not found"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTableIfPresent(AddReservationRequest request) {
        List<Table> table = tableDatabase.findByTitleTable(request.getTableTitle());
        return table.isEmpty()
                ? Optional.of(new CoreError("info about table", "was not found"))
                : Optional.empty();
    }
}
