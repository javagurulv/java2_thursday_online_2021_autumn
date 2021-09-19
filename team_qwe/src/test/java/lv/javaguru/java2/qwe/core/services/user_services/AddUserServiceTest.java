package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Type;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.AddUserResponse;
import lv.javaguru.java2.qwe.core.services.matchers.UserMatcher;
import lv.javaguru.java2.qwe.core.services.validator.AddUserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddUserServiceTest {

    @Mock
    private Database database;
    @Mock
    private UserData userData;
    @Mock
    private AddUserValidator validator;
    @InjectMocks
    private AddUserService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddUserRequest request = new AddUserRequest("Marina", "150",
                "SUPER_RICH", "500000.0");
        List<CoreError> errors = List.of(
                new CoreError("Age", "user of maximum 130 years old is allowed!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddUserResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "Age");
        assertEquals(errors.get(0).getMessage(), "user of maximum 130 years old is allowed!");
        Mockito.verifyNoInteractions(userData);
    }

    @Test
    public void shouldAddUserToUserData() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddUserRequest request = new AddUserRequest("Marina", "42",
                "WEALTHY", "500000");
        AddUserResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(userData).addUser(argThat(new UserMatcher(new User(
                "Marina", 42, Type.WEALTHY, 500_000)
        )));
    }

}