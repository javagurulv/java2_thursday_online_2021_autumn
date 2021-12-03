package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.AddUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.AddUserValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.matcher.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddAllUserServiceTest {

    @Mock private UsersRepository usersRepository;
    @Mock private AddUserValidator validator;
    @InjectMocks private AddAllUsersService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddUserRequest notValidRequest = new AddUserRequest(null, "surname", "252525");
        when(validator.coreErrors(notValidRequest)).thenReturn(List.of(new CoreError("name", "Shouldn't be empty")));
        AddUsersResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidator() {
        AddUserRequest notValidRequest = new AddUserRequest(null, "surname", "252525");
        when(validator.coreErrors(notValidRequest)).thenReturn(List.of(new CoreError("name", "Shouldn't be empty")));
        AddUsersResponse addUsersResponse = service.execute(notValidRequest);
        assertEquals(addUsersResponse.getErrorsList().size(), 1);
        assertEquals(addUsersResponse.getErrorsList().get(0).getField(), "name");
        assertEquals(addUsersResponse.getErrorsList().get(0).getMessageError(), "Shouldn't be empty");
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
        AddUserRequest notValidRequest = new AddUserRequest(null, "surname", "252525");
        when(validator.coreErrors(notValidRequest)).thenReturn(List.of(new CoreError("name", "Shouldn't be empty")));
        service.execute(notValidRequest);
        verifyNoInteractions(usersRepository);
    }

    @Test
    public void shouldAddVisitorToDatabaseWhenRequestIsValid() {
        AddUserRequest validRequest = new AddUserRequest("name", "surname", "252525");
        when(validator.coreErrors(validRequest)).thenReturn(List.of());
        service.execute(validRequest);
        verify(usersRepository).saveUserToRestaurantList(argThat(new Matchers("name", "surname", "252525")));
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        AddUserRequest validRequest = new AddUserRequest("name", "surname", "252525");
        when((validator.coreErrors(validRequest))).thenReturn(List.of());
        AddUsersResponse response = service.execute(validRequest);
        assertFalse(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithVisitorWhenRequestIsValid() {
        AddUserRequest validRequest = new AddUserRequest("name", "surname", "252525");
        when(validator.coreErrors(validRequest)).thenReturn(List.of());
        AddUsersResponse response = service.execute(validRequest);
        assertNotNull(response.getNewUser());
        assertEquals(response.getNewUser().getUserName(), validRequest.getName());
        assertEquals(response.getNewUser().getSurname(), validRequest.getSurname());
    }
}