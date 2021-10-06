package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Position;
import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.Type;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserPortfolioValidator;
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
public class GetUserPortfolioServiceTest {

    @Mock private UserData userData;
    @Mock private GetUserPortfolioValidator validator;
    @InjectMocks private GetUserPortfolioService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        GetUserPortfolioRequest request =
                new GetUserPortfolioRequest("");
        List<CoreError> errors = List.of(
                new CoreError("UserName", "must not be empty!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        GetUserPortfolioResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "UserName");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    public void shouldReturnUserPortfolioResult() {
        GetUserPortfolioRequest request =
                new GetUserPortfolioRequest("Alexander");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        User user = new User("Alexander", 25, Type.SUPER_RICH, 1_000_000);
        List<Position> portfolio = List.of(
                new Position(new Stock("Alibaba", "Technology", "USD", 175.23, 0, 1.32), 100, 175.23),
                new Position(new Stock("Gazprom", "Energy", "USD", 9.25, 0.51, 0.95), 2500, 9.25),
                new Position(new Stock("Pfizer", "Health care", "USD", 78.93, 0.64, 0.98), 1000, 78.93)
        );
        user.setPortfolio(portfolio);
        Mockito.when(userData.findUserByName("Alexander")).thenReturn(Optional.of(user));

        GetUserPortfolioResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Assert.assertEquals(user.getPortfolio(), response.getPortfolio());
    }

}