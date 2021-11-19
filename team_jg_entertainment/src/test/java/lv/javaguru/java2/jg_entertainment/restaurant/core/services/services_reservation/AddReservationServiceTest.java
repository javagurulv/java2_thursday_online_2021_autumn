package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.AddReservationValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import lv.javaguru.java2.jg_entertainment.restaurant.matchers_reservation.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.argThat;
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

    @Test
    public void shouldAddReservation() {
        Visitor visitor = new Visitor("name", "surname", "3723");
        visitor.setIdClient(10L);
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(visitor);
        Table table = new Table("title", 1, 10.00);
        table.setId(20L);
        List<Table> tableList = new ArrayList<>();
        tableList.add(table);
        Menu menu = new Menu("menu", "description", 10.00);
        menu.setNumber(30L);
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu);
        Mockito.when(databaseVisitors.findClientById(10L)).thenReturn(visitorList);
        Mockito.when(databaseTable.findTableById(20L)).thenReturn(tableList);
        Mockito.when(databaseMenu.findById(30L)).thenReturn(menuList);
        AddReservationRequest request = new AddReservationRequest(visitor.getIdClient().toString(),
                menu.getNumber().toString(), table.getId().toString(), "2021-11-25 19:00");
        AddReservationResponse response = service.execute(request);
        assertFalse(response.hasError());
        Mockito.verify(databaseReservation).addReservation(argThat(new Matchers(response.getReservation().getVisitor(),
                response.getReservation().getMenu(), response.getReservation().getTable(), response.getReservation().getReservationDate())));

    }
}