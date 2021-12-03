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
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.matcher.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddReservationServiceTest {

    @Mock private ReservationRepository databaseReservation;
    @Mock private UsersRepository databaseVisitors;
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

    @Test
    public void shouldAddReservation() {
        User user = new User("name", "surname", "3723");
        user.setUserId(10L);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Table table = new Table("title", 1, 10.00);
        table.setId(20L);
        List<Table> tableList = new ArrayList<>();
        tableList.add(table);
        Menu menu = new Menu("menu", "description", 10.00);
        menu.setNumber(30L);
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu);
        Mockito.when(databaseVisitors.findUserById(10L)).thenReturn(userList);
        Mockito.when(databaseTable.findTableById(20L)).thenReturn(tableList);
        Mockito.when(databaseMenu.findById(30L)).thenReturn(menuList);
        AddReservationRequest request = new AddReservationRequest(user.getUserId().toString(),
                menu.getNumber().toString(), table.getId().toString(), "2021-11-25 19:00");
        AddReservationResponse response = service.execute(request);
        assertFalse(response.hasError());
        Mockito.verify(databaseReservation).addReservation(argThat(new Matchers(response.getReservation().getUser(),
                response.getReservation().getMenu(), response.getReservation().getTable(), response.getReservation().getReservationDate())));
    }

    @Test
    public void shouldReturnNotCorrectDateEnterError(){
        AddReservationRequest request = new AddReservationRequest("10L", "20L", "30L", "10/10/2022 19:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not correct!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Date");
        assertEquals(response.getErrorList().get(0).getMessageError(), "is not correct!");
        Mockito.verifyNoInteractions(databaseReservation);
    }

    @Test
    public void shouldReturnReservedDateError(){
        AddReservationRequest request = new AddReservationRequest("10L", "20L", "30L", "10-10-2022 19:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "all is reserved"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Date");
        assertEquals(response.getErrorList().get(0).getMessageError(), "all is reserved");
        Mockito.verifyNoInteractions(databaseReservation);
    }

    @Test
    public void shouldReturnTimeNotCorrectError(){
        AddReservationRequest request = new AddReservationRequest("10L", "20L", "30L", "10-10-2022 19:45");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Minutes", "is not correct!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Minutes");
        assertEquals(response.getErrorList().get(0).getMessageError(), "is not correct!");
        Mockito.verifyNoInteractions(databaseReservation);
    }

    @Test
    public void shouldReturnHourError(){
        AddReservationRequest request = new AddReservationRequest("10L", "20L", "30L", "10-10-2022 10:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Time in the date", "is not working hour!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Time in the date");
        assertEquals(response.getErrorList().get(0).getMessageError(), "is not working hour!");
        Mockito.verifyNoInteractions(databaseReservation);
    }

    @Test
    public void shouldReturnNotFutureError(){
        AddReservationRequest request = new AddReservationRequest("10L", "20L", "30L", "10-10-1985 19:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not in the future!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddReservationResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "Date");
        assertEquals(response.getErrorList().get(0).getMessageError(), "is not in the future!");
        Mockito.verifyNoInteractions(databaseReservation);
    }
}