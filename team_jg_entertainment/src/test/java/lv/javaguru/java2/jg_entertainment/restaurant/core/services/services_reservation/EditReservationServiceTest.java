package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.EditReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.EditReservationValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EditReservationServiceTest {

    @Mock private ReservationRepository database;
    @Mock private EditReservationValidator validator;
    @InjectMocks private EditReservationService service;

    @Test
    public void shouldReturnErrorIfReservationNull(){
        EditReservationRequest request = new EditReservationRequest(null, "ID_VISITOR", "0000");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(new CoreError("id", "must not be null"));
        Mockito.when(validator.validate(request)).thenReturn(errorList);
        EditReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "id");
        assertEquals(response.getErrorList().get(0).getMessageError(), "must not be null");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnErrorIfReservationInfoNotCorrect(){
        EditReservationRequest request = new EditReservationRequest("null", "ID_VISITOR", "0000");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(new CoreError("id", "must contain the number"));
        Mockito.when(validator.validate(request)).thenReturn(errorList);
        EditReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "id");
        assertEquals(response.getErrorList().get(0).getMessageError(), "must contain the number");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnErrorIfEnumInfoNotCorrect(){
        EditReservationRequest request = new EditReservationRequest("null", "enum", "0000");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(new CoreError("edit", "should be like ID_VISITOR, ID_TABLE, ID_MENU, RESERVATION_DATE"));
        Mockito.when(validator.validate(request)).thenReturn(errorList);
        EditReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "edit");
        assertEquals(response.getErrorList().get(0).getMessageError(), "should be like ID_VISITOR, ID_TABLE, ID_MENU, RESERVATION_DATE");
        Mockito.verifyNoInteractions(database);
    }
}