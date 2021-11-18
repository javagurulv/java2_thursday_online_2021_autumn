package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.AddReservationValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddReservationServiceTest {

    @Mock private ReservationRepository databaseReservation;
    @Mock private VisitorsRepository databaseVisitors;
    @Mock private MenuRepository databaseMenu;
    @Mock private TableRepository databaseTable;
    @Mock private AddReservationValidator validator;
    @InjectMocks private AddReservationService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddReservationRequest notValidRequest = new AddReservationRequest(null, "1", "1", "11/12/2021");
        when(validator.validate(notValidRequest))
                .thenReturn(List.of(new CoreError("Visitor ID", "Must not be empty!")));
        AddReservationResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }
}