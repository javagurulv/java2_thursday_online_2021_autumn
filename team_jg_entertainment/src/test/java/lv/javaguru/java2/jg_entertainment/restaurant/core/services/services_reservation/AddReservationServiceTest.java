package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.*;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.AddReservationValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import lv.javaguru.java2.jg_entertainment.restaurant.matchers_reservation.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddReservationServiceTest {

    @Mock private DatabaseReservationImpl databaseReservation;
    @Mock private DatabaseVisitorsImpl databaseVisitors;
    @Mock private DatabaseMenuImpl databaseMenu;
    @Mock private DatabaseTableImpl databaseTable;
    @Mock private AddReservationValidator validator;
    @InjectMocks private AddReservationService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddReservationRequest notValidRequest = new AddReservationRequest(null, "252525L", "title", "title", "11/12/2021");
        when(validator.validate(notValidRequest))
                .thenReturn(List.of(new CoreError("visitorName", "Must not be empty!")));
        AddReservationResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails1() {
        AddReservationRequest notValidRequest = new AddReservationRequest("name", "123", null, "title", "11/12/2021");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(new CoreError("menuTitle", "Must not be empty!"));
        when(validator.validate(notValidRequest))
                .thenReturn(errorList);
        AddReservationResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "menuTitle");
        assertEquals(response.getErrorList().get(0).getMessageError(), "Must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails2() {
        AddReservationRequest notValidRequest = new AddReservationRequest("name", null, "title", "title", "11/12/2021");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(new CoreError("telephoneNumber", "Must not be empty or less than 0!"));
        when(validator.validate(notValidRequest))
                .thenReturn(errorList);
        AddReservationResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
        assertEquals(response.getErrorList().size(), 1);
        assertEquals(response.getErrorList().get(0).getField(), "telephoneNumber");
        assertEquals(response.getErrorList().get(0).getMessageError(), "Must not be empty or less than 0!");
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails3() {
        AddReservationRequest notValidRequest = new AddReservationRequest("name", "252525L", "title", null, "11/12/2021");
        when(validator.validate(notValidRequest))
                .thenReturn(List.of(new CoreError("tableTitle", "Must not be empty!")));
        AddReservationResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails4() {
        AddReservationRequest notValidRequest =
                new AddReservationRequest("name", "252525", "title", "title", " ");
        when(validator.validate(notValidRequest))
                .thenReturn(List.of(new CoreError("date", "Must not be empty!")));
        AddReservationResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldAddAllInformationToReservationWhenRequestIsValid() {
        Visitors visitors = new Visitors("name", "surname", "1");
        Menu menu = new Menu("title", "description", 2.0);
        Table table = new Table("title", 2, 2.0);
        List<Visitors> visitorsList = new ArrayList<>();
        visitorsList.add(visitors);
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu);
        List<Table> tableList = new ArrayList<>();
        tableList.add(table);
        when(databaseVisitors.findVisitorsByNameAndTelephoneNumber(visitors.getClientName(), visitors.getTelephoneNumber())).thenReturn(visitorsList);
        when(databaseMenu.findByTitle(menu.getTitle())).thenReturn(menuList);
        when(databaseTable.findByTitleTable(table.getTitle())).thenReturn(tableList);
        AddReservationRequest validRequest = new AddReservationRequest(visitors.getClientName(), visitors.getTelephoneNumber(),
                menu.getTitle(), table.getTitle(), "11/12/2021");
        AddReservationResponse response = service.execute(validRequest);
        verify(databaseReservation)
                .addReservation(argThat(new Matchers(response.getReservation().getVisitor(),
                        response.getReservation().getMenu(),
                        response.getReservation().getTable(),
                        response.getReservation().getReservationDate())));
    }
}