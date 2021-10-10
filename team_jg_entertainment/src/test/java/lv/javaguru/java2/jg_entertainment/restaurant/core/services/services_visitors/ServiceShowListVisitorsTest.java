package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceShowListVisitorsTest {

    @Mock
    private DatabaseVisitors databaseVisitors;
    @InjectMocks
    private ServiceShowListVisitors service;

    @Test
    public void shouldGetVisitorsFromDb() {
        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name", "surname"));
        Mockito.when(databaseVisitors.showAllClientsInList()).thenReturn(visitors);
        ShowAllVisitorsRequest request = new ShowAllVisitorsRequest();
        ResponseShowAllVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getNewVisitor().size(), 1);
        assertEquals(response.getNewVisitor().get(0).getClientName(), "name");
        assertEquals(response.getNewVisitor().get(0).getSurname(), "surname");
    }
}