package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorDeleteVisitor;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
class ServiceDeleteVisitorsTest {

    @Mock
    private ImplDatabaseVisitors database;
    @Mock
    private ValidatorDeleteVisitor validator;
    @InjectMocks
    private ServiceDeleteVisitors service;

    @BeforeEach
    public void before() {
        database = Mockito.mock(ImplDatabaseVisitors.class);
        validator = Mockito.mock(ValidatorDeleteVisitor.class);
        service = new ServiceDeleteVisitors(database, validator);
    }

    @Test
    public void shouldReturnErrorWhenVisitorIDNotProvided() {
        RequestDeleteVisitor request = new RequestDeleteVisitor(null, "name");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id visitor", "Can't be null"));
        Mockito.when(validator.coreErrors(request)).thenReturn(errors);
        ResponseDeleteVisitors response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().get(0).getField(), "id visitor");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Can't be null");
    }

    @Test
    public void shouldDeleteVisitorWithId(){
        Mockito.when(validator.coreErrors(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteClientWithIDAndName(2L, "name")).thenReturn(true);
        RequestDeleteVisitor request = new RequestDeleteVisitor(2L, "name");
        ResponseDeleteVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertTrue(response.ifIdVisitorDelete());

    }
}