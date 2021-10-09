package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.domain.Type;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.FindUserByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindUserByNameValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class FindUserByNameServiceTest {

    @Mock private UserData userData;
    @Mock private FindUserByNameValidator validator;
    @InjectMocks private FindUserByNameService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        FindUserByNameRequest request = new FindUserByNameRequest("");
        List<CoreError> errors = List.of(
                new CoreError("Name", "minimum 3 symbols required!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        FindUserByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "minimum 3 symbols required!");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    public void shouldReturnUserSearchResult() {
        FindUserByNameRequest request = new FindUserByNameRequest("Alexander");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        Optional<User> user = Optional.of(
                new User("Alexander", 25, Type.SUPER_RICH, 1_000_000)
        );
        Mockito.when(userData.findUserByName("Alexander")).thenReturn(user);

        FindUserByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Assert.assertEquals(response.getUser(), new User("Alexander", 25,
                Type.SUPER_RICH, 1_000_000)
        );
    }

}