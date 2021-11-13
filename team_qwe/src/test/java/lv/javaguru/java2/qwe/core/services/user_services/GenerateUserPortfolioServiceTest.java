package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.Type;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.GenerateUserPortfolioValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GenerateUserPortfolioServiceTest {

    @Mock private UserData userData;
    @Mock private GenerateUserPortfolioValidator validator;
    @InjectMocks private GenerateUserPortfolioService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails1() {
        GenerateUserPortfolioRequest request = new GenerateUserPortfolioRequest("");
        List<CoreError> errors = List.of(
                new CoreError("UserName", "must not be empty!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(userData.findUserByIdOrName("")).thenReturn(Optional.empty());
        GenerateUserPortfolioResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertTrue(errors.contains(new CoreError("UserName", "must not be empty!")));
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails2() {
        GenerateUserPortfolioRequest request = new GenerateUserPortfolioRequest("Alexander");
        List<CoreError> errors = List.of(
                new CoreError("", "portfolio has been already generated for this user!")
        );
        User user = new User("Alexander", 25, Type.SUPER_RICH, 1_000_000);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        GenerateUserPortfolioResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertTrue(errors.contains(new CoreError("", "portfolio has been already generated for this user!")));
    }

}