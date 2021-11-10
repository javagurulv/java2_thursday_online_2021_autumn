package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Bond;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddBondRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddBondResponse;
import lv.javaguru.java2.qwe.core.services.matchers.BondMatcher;
import lv.javaguru.java2.qwe.core.services.validator.AddBondValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddBondServiceTest {

    @Mock private Database database;
    @Mock private AddBondValidator validator;
    @InjectMocks private AddBondService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddBondRequest request = new AddBondRequest(
                "GAZPRU", "", "Energy", "USD", "108.75",
                "4.75", "BBB+", "1000", "2031-12-31");
        List<CoreError> errors = List.of(
                new CoreError("Name", "3 to 100 symbols required!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddBondResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "3 to 100 symbols required!");

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldAddBondToDatabase() {
        System.out.println(LocalDate.of(2031, 12, 31));
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddBondRequest request = new AddBondRequest(
                "GAZPRU", "Gazprom 4.75 31/12/2031", "Energy", "USD", "108.75",
                "4.75", "BBB+", "1000", "2031-12-31");
        AddBondResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).addBond(argThat(new BondMatcher(new Bond(
                "GAZPRU","Gazprom 4.75 31/12/2031", "Energy", "USD", 108.75,
                4.75, "BBB+", 1000, LocalDate.of(2031, 12, 31)
        ))));
    }

}