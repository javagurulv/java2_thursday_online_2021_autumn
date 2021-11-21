package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.DeleteUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.DeleteUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.DeleteUserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserServiceTest {

    @Mock private UsersRepository database;
    @Mock private DeleteUserValidator validator;
    @InjectMocks private DeleteUsersService service;

    @Test
    public void shouldReturnErrorWhenVisitorIdNotProvided() {
        DeleteUserRequest request = new DeleteUserRequest(null, "name");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id visitor", "Can't be null"));
        Mockito.when(validator.coreErrors(request)).thenReturn(errors);
        DeleteUsersResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(), 1);
        assertEquals(response.getErrorsList().get(0).getField(), "id visitor");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Can't be null");
    }

    @Test
    public void shouldReturnErrorWhenVisitorNameNotProvided() {
        DeleteUserRequest request = new DeleteUserRequest(1L, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name visitor", "Can't be empty"));
        Mockito.when(validator.coreErrors(request)).thenReturn(errors);
        DeleteUsersResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(), 1);
        assertEquals(response.getErrorsList().get(0).getField(), "name visitor");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Can't be empty");
    }

    @Test
    public void shouldDeleteVisitorWithIdFromDatabase() {
        Mockito.when(validator.coreErrors(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteUserWithIDAndName(1L, "name")).thenReturn(true);
        DeleteUserRequest request = new DeleteUserRequest(1L, "name");
        DeleteUsersResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertTrue(response.ifUserIdDeleted());
    }
}