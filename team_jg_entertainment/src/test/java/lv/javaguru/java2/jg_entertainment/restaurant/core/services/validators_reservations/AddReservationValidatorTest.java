package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.menu_repository.MenuRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.DateValidatorExecution;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AddReservationValidatorTest {

    @Mock private UsersRepository databaseVisitor;
    @Mock private MenuRepository databaseMenu;
    @Mock private TableRepository databaseTable;
    @Mock private ExistReservationValidator existReservationValidator;
    @Mock private DateValidatorExecution dateValidatorExecution;
    @Mock private ReservationLongNumChecker numChecker;
    @InjectMocks private AddReservationValidator validator;

    @Test
    public void shouldReturnErrorsWithUserMenuTable(){
        AddReservationRequest request = new AddReservationRequest("", "", "", "2021-12-12 19:00");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        Mockito.verifyNoInteractions(databaseVisitor);
        Mockito.verifyNoInteractions(databaseMenu);
        Mockito.verifyNoInteractions(databaseTable);
    }

    @Test
    public void shouldReturnEmptyListWithErrors(){
        AddReservationRequest request = new AddReservationRequest("1", "1", "1", "20-11-2021 19:00");
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname", "3723"));
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("title", "description", 10));
        List<Table> tables = new ArrayList<>();
        tables.add(new Table("title", 1, 10));
        Mockito.when(databaseVisitor.findUserById(Long.valueOf(request.getUserID()))).thenReturn(users);
        Mockito.when(databaseMenu.findById(Long.valueOf(request.getMenuID()))).thenReturn(menus);
        Mockito.when(databaseTable.findTableById(Long.valueOf(request.getTableID()))).thenReturn(tables);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}