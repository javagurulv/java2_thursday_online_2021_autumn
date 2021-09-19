package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.RemoveUserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUserServiceTest {

    @Mock
    private UserData userData;
    @InjectMocks
    private RemoveUserService service;

    @Test
    public void shouldRemoveUserByName() {
        Mockito.when(userData.removeUser("Alexander")).thenReturn(true);
        RemoveUserRequest request = new RemoveUserRequest("Alexander");
        RemoveUserResponse response = service.execute(request);
        assertTrue(response.isRemoved());
    }

    @Test
    public void returnNoUserWithSuchName() {
        RemoveUserRequest request = new RemoveUserRequest("Marina");
        RemoveUserResponse response = service.execute(request);
        assertFalse(response.isRemoved());
    }

}