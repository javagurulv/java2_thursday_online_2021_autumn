package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByNameRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByNameResponse;
import lv.javaguru.java2.qwe.core.services.validator.FindSecurityByNameValidator;
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
public class FindSecurityByNameServiceTest {

    @Mock private Database database;
    @Mock private FindSecurityByNameValidator validator;
    @InjectMocks private FindSecurityByNameService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        FindSecurityByNameRequest request = new FindSecurityByNameRequest("");
        List<CoreError> errors = List.of(
                new CoreError("Name", "minimum 3 symbols required!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        FindSecurityByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "minimum 3 symbols required!");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    public void shouldReturnSearchResult() {
        FindSecurityByNameRequest request = new FindSecurityByNameRequest("Alibaba");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        Optional<Security> security = Optional.of(
                new Stock("Alibaba", "Technology", "USD",
                        175.32, 0, 1.32)
        );
        Mockito.when(database.findSecurityByName("Alibaba")).thenReturn(security);

        FindSecurityByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Assert.assertEquals(response.getSecurity(), new Stock("Alibaba", "Technology", "USD",
                175.32, 0, 1.32));
    }

}