package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.domain.*;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioSummaryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioSummaryResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserPortfolioSummaryValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class GetUserPortfolioSummaryServiceTest {

    @Mock private UserData userData;
    @Mock private GetUserPortfolioSummaryValidator validator;
    @InjectMocks private GetUserPortfolioSummaryService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        GetUserPortfolioSummaryRequest request =
                new GetUserPortfolioSummaryRequest("");
        List<CoreError> errors = List.of(
                new CoreError("UserName", "must not be empty!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        GetUserPortfolioSummaryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "UserName");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    public void shouldReturnUserPortfolioSummaryResult() {
        GetUserPortfolioSummaryRequest request =
                new GetUserPortfolioSummaryRequest("Alexander");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        User user = new User(1, "Alexander", 25, Type.SUPER_RICH, 1_000_000);
        List<Position> portfolio = List.of(
                new Position(new Stock("BABA US", "Alibaba", "Technology", "USD", 175.23, 0, 1.32), 525, 175.23),
                new Position(new Stock("OGZD LN", "Gazprom", "Energy", "USD", 9.25, 5.53, 0.95), 7563, 9.25),
                new Position(new Stock("PFA US", "Pfizer", "Health care", "USD", 78.93, 2.18, 0.98), 1000, 78.93),
                new Position(new Cash(), 1055.34, 1)
        );
        user.setPortfolio(portfolio);
        user.setPortfolioGenerationDate(LocalDate.now());

        int userRiskTolerance = 5;
        double userInitialInvestment = 1_000_000;
        LocalDate portfolioGenerationDate = LocalDate.now();
        double portfolioValue = 241_938.84;
        int amountOfPositions = 4;
        double avgWgtDividendYield = 2.3102;
        double avgWgtRiskWeight = 1.0963;

        Mockito.when(userData.findUserByName("Alexander")).thenReturn(Optional.of(user));

        GetUserPortfolioSummaryResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Assert.assertEquals(userRiskTolerance, response.getUserRiskTolerance());
        Assert.assertEquals(userInitialInvestment, response.getUserInitialInvestment(), 0.01);
        Assert.assertEquals(portfolioGenerationDate, response.getPortfolioGenerationDate());
        Assert.assertEquals(portfolioValue, response.getPortfolioValue(), 0.01);
        Assert.assertEquals(amountOfPositions, response.getAmountOfPositions());
        Assert.assertEquals(avgWgtDividendYield, response.getAvgWgtDividendYield(), 0.001);
        Assert.assertEquals(avgWgtRiskWeight, response.getAvgWgtRiskWeight(), 0.001);
    }

}