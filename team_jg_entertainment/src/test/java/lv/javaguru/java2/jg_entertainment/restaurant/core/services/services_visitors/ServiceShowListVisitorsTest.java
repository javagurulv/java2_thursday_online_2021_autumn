package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.Visitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestShowAllVisitorsInListRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceShowListVisitorsTest {

    @Mock
    private ImplDatabaseVisitors database;
    @InjectMocks
    private ServiceShowListVisitors showAllVisitors;


    @BeforeEach
    public void setUp() {
        database = Mockito.mock(ImplDatabaseVisitors.class);
        showAllVisitors = new ServiceShowListVisitors(database);
    }

    @Test
    public void shouldAllVisitors() {
        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name", "surname", 3659874L));
        Mockito.when(database.showAllClientsInList()).thenReturn(visitors);

        RequestShowAllVisitorsInListRestaurant request = new RequestShowAllVisitorsInListRestaurant();
        ResponseShowAllVisitors response = showAllVisitors.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getNewVisitor().size(), 1);
        assertEquals(response.getNewVisitor().get(0).getClientName(), "name");
        assertEquals(response.getNewVisitor().get(0).getSurname(), "surname");
        assertEquals(response.getNewVisitor().get(0).getTelephoneNumber(), 3659874L);
    }
}