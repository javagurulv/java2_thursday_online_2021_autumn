package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import lv.javaguru.java2.qwe.core.services.validator.RemoveUserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUserServiceTest {

    @Mock private UserData userData;
    @Mock private RemoveUserValidator validator;
    @InjectMocks private RemoveUserService service;

    @Test
    public void shouldRemoveUserByName() {
        Mockito.when(userData.removeUser("Alexander")).thenReturn(true);
        RemoveUserRequest request = new RemoveUserRequest("Alexander");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        RemoveUserResponse response = service.execute(request);
        assertTrue(response.isRemoved());
    }

    @Test
    public void returnNoUserWithSuchName() {
        RemoveUserRequest request = new RemoveUserRequest("Marina");
        List<CoreError> errors = List.of(
                new CoreError("Name", "No user with such name!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveUserResponse response = service.execute(request);
        assertFalse(response.isRemoved());
    }

    @Test
    public void returnPortfolioIsNotEmpty() {
        RemoveUserRequest request = new RemoveUserRequest("Marina");
        List<CoreError> errors = List.of(
                new CoreError("Name", "can't remove user, because there are securities in the portfolio!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveUserResponse response = service.execute(request);
        assertFalse(response.isRemoved());
    }

}