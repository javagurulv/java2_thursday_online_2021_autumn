package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.DeleteReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteReservationValidatorTest {

    @Mock private ExistReservationValidator reservationValidator;
    @InjectMocks  private DeleteReservationValidator validator;

    @Test
    public void returnIdReservationError() {
        DeleteReservationRequest request = new DeleteReservationRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
    }

    @Test
    public void returnReservationErrorWhenReservationWasNotFind() {
        Mockito.when(reservationValidator.validate(12L)).thenReturn(Optional.of(new CoreError("reservation", "was not find")));
        DeleteReservationRequest request = new DeleteReservationRequest(12L);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
    }
    @Test
    public void returnReservationWithoutErrors() {
        DeleteReservationRequest request = new DeleteReservationRequest(12L);
        Mockito.when(reservationValidator.validate(12L)).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}
