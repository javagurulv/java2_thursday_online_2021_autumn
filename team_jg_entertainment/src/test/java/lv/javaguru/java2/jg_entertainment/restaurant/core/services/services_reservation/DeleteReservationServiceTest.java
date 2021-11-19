package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.DeleteReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.DeleteReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.DeleteReservationValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DeleteReservationServiceTest {

    @Mock private ReservationRepository databaseReservation;
    @Mock private DeleteReservationValidator validator;
    @InjectMocks private DeleteReservationService service;

    @Test
    public void shouldReturnEmptyId(){
        DeleteReservationRequest request = new DeleteReservationRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("idReservation", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "idReservation");
        assertEquals(response.getErrorList().get(0).getMessageError(),"must not be empty");
    }

    @Test
    public void shouldCorrectRemoveReservation(){
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(databaseReservation.removeReservation(10L)).thenReturn(true);
        DeleteReservationRequest request = new DeleteReservationRequest(10L);
        DeleteReservationResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertTrue(response.isTrue());
    }
}