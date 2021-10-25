package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.domain.Type;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserInvestmentsByEachIndustryRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserInvestmentsByEachIndustryResponse;
import lv.javaguru.java2.qwe.core.services.validator.GetUserInvestmentsByEachIndustryValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class GetUserInvestmentsByEachIndustryServiceTest {

    @Mock private UserData userData;
    @Mock private GetUserInvestmentsByEachIndustryValidator validator;
    @InjectMocks private GetUserInvestmentsByEachIndustryService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        GetUserInvestmentsByEachIndustryRequest request =
                new GetUserInvestmentsByEachIndustryRequest("");
        List<CoreError> errors = List.of(
                new CoreError("UserName", "must not be empty!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        GetUserInvestmentsByEachIndustryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "UserName");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    public void shouldReturnUserInvestmentsByEachIndustryResult() {
        GetUserInvestmentsByEachIndustryRequest request =
                new GetUserInvestmentsByEachIndustryRequest("Alexander");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        User user = new User(1, "Alexander", 25, Type.SUPER_RICH, 1_000_000);
        Map<String, Double> map = Map.ofEntries(
                Map.entry("Technology", 50000.),
                Map.entry("Energy", 25000.),
                Map.entry("Health care", 75000.)
        );
        Mockito.when(userData.findUserByName("Alexander")).thenReturn(Optional.of(user));
        Mockito.when(userData.getUserInvestmentsByEachIndustry(user)).thenReturn(map);

        GetUserInvestmentsByEachIndustryResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Assert.assertEquals(map, response.getInvestmentMap());
    }

}