package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.*;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserTradesRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserTradesResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserTradesValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetUserTradesServiceTest {

    @Mock private UserData userData;
    @Mock private GetUserTradesValidator validator;
    @InjectMocks private GetUserTradesService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        GetUserTradesRequest request =
                new GetUserTradesRequest("");
        List<CoreError> errors = List.of(
                new CoreError("UserName", "must not be empty!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        GetUserTradesResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "UserName");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    public void shouldReturnUserTradesResult() {
        GetUserTradesRequest request =
                new GetUserTradesRequest("Alexander");
        User user = new User("Alexander", 25, Type.SUPER_RICH, 1_000_000);
        List<TradeTicket> trades = List.of(
                new TradeTicket(user, new Stock("BABA US", "Alibaba", "Technology", "USD", 175.23, 0, 1.32),
                        TradeType.BUY, 100., 175.23, LocalDateTime.of(LocalDate.of(2021, 11, 2), LocalTime.of(16, 33, 27))),
                new TradeTicket(user, new Stock("OGZD LN", "Gazprom", "Energy", "USD", 9.25, 0.51, 0.95),
                        TradeType.SELL, 2500., 9.25, LocalDateTime.of(LocalDate.of(2021, 11, 4), LocalTime.of(17, 13, 56))),
                new TradeTicket(user, new Stock("PFE US", "Pfizer", "Health care", "USD", 78.93, 0.64, 0.98),
                        TradeType.BUY, 1000., 78.93, LocalDateTime.of(LocalDate.of(2021, 11, 5), LocalTime.of(10, 5, 33)))
        );
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(userData.findUserByIdOrName("Alexander")).thenReturn(Optional.of(user));
        Mockito.when(userData.getUserTrades(user.getId())).thenReturn(trades);
        GetUserTradesResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Assert.assertEquals(trades, response.getTrades());
    }

}