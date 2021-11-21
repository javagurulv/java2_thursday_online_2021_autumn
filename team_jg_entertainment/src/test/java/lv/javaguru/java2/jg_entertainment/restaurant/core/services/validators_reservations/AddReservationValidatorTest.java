package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.DateValidatorExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class AddReservationValidatorTest {

    @Mock private UsersRepository databaseVisitor;
    @Mock private MenuRepository databaseMenu;
    @Mock private TableRepository databaseTable;
    @Mock private DateValidatorExecution dateValidatorExecution;
    @Mock private ReservationLongNumChecker numChecker;
    @InjectMocks private AddReservationValidator validator;

    @Test
    public void shouldReturnVisitorError(){
        AddReservationRequest request = new AddReservationRequest("", "", "", "20-11-2021 19:00");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 3);
        Mockito.verifyNoInteractions(databaseVisitor);
    }
}