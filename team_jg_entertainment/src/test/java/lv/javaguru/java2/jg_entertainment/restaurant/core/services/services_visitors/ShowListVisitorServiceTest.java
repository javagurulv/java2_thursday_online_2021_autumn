package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ShowAllVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
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
public class ShowListVisitorServiceTest {

    @Mock private VisitorsRepository visitorsRepository;
    @InjectMocks private ShowListVisitorsService service;

    @Test
    public void shouldGetVisitorsFromDb() {
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname"));
        Mockito.when(visitorsRepository.showAllClientsInList()).thenReturn(visitors);
        ShowAllVisitorsRequest request = new ShowAllVisitorsRequest();
        ShowAllVisitorsResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getNewVisitor().size(), 1);
        assertEquals(response.getNewVisitor().get(0).getClientName(), "name");
        assertEquals(response.getNewVisitor().get(0).getSurname(), "surname");
    }
}