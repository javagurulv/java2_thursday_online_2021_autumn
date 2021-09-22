package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.matchers.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class ServiceAddAllVisitorsTest {

    @Mock
    private ImplDatabaseVisitors database;
    @Mock
    private ValidatorAddVisitor validator;
    @InjectMocks
    private ServiceAddAllVisitors serviceAddVisitors;

    @BeforeEach
    public void before(){
        database = Mockito.mock(ImplDatabaseVisitors.class);
        validator = Mockito.mock(ValidatorAddVisitor.class);
        serviceAddVisitors = new ServiceAddAllVisitors(database, validator);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        RequestAddVisitor requestAddVisitor = new RequestAddVisitor(null, "surname", 326598L);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name visitors", "Shouldn't be empty"));
        Mockito.when(validator.coreErrors(requestAddVisitor)).thenReturn(errors);

        ResponseAddVisitor response = serviceAddVisitors.execute(requestAddVisitor);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(),1);
        assertEquals(response.getErrorsList().get(0).getField(),"name visitors");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Shouldn't be empty");
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldAddVisitorsInRestaurant(){
        Mockito.when(validator.coreErrors(any())).thenReturn(new ArrayList<>());
        RequestAddVisitor request = new RequestAddVisitor("name", "surname", 326598L);
        ResponseAddVisitor response = serviceAddVisitors.execute(request);
        assertFalse(response.hasError());
        Mockito.verify(database).saveClientToRestaurantList(
                argThat(new Matchers("name", "surname", 326598L)));
    }
}