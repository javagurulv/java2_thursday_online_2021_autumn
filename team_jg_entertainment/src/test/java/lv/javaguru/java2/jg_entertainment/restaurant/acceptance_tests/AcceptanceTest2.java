package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseSearchVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceAddAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceSearchVisitors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest2 {

    private ApplicationContext appContext =
            new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);

    @Test
    public void searchVisitor() {
        AddVisitorRequest request1 = new AddVisitorRequest("name", "surname1", 252525L);
        getAddVisitorService().execute(request1);
        AddVisitorRequest request2 = new AddVisitorRequest("name", "surname2", 252525L);
        getAddVisitorService().execute(request2);

        SearchVisitorsRequest request3 = new SearchVisitorsRequest("name", null);
        ResponseSearchVisitors response = getSearchVisitorService().execute(request3);

        Assertions.assertEquals(response.getVisitors().size(), 2);
        Assertions.assertEquals(response.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(0).getSurname(), "surname1");
        Assertions.assertEquals(response.getVisitors().get(1).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(1).getSurname(), "surname2");
    }

    @Test
    public void searchVisitorsOrderingDescending() {
        AddVisitorRequest request1 = new AddVisitorRequest("name", "surname1", 252525L);
        getAddVisitorService().execute(request1);
        AddVisitorRequest request2 = new AddVisitorRequest("name", "surname2", 252525L);
        getAddVisitorService().execute(request2);

        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        ResponseSearchVisitors response = getSearchVisitorService().execute(request);

        Assertions.assertEquals(response.getVisitors().size(), 2);
        Assertions.assertEquals(response.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(0).getSurname(), "surname2");
        Assertions.assertEquals(response.getVisitors().get(1).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(1).getSurname(), "surname1");
    }

    @Test
    public void searchVisitorsOrderingAscending() {
        AddVisitorRequest addVisitorRequest = new AddVisitorRequest("name", "surname1", 252525L);
        getAddVisitorService().execute(addVisitorRequest);
        AddVisitorRequest addVisitorRequest1 = new AddVisitorRequest("name", "surname2", 252525L);
        getAddVisitorService().execute(addVisitorRequest1);

        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        ResponseSearchVisitors responseSearchVisitors = getSearchVisitorService().execute(request);

        Assertions.assertEquals(responseSearchVisitors.getVisitors().size(), 2);
        Assertions.assertEquals(responseSearchVisitors.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(responseSearchVisitors.getVisitors().get(0).getSurname(), "surname1");
        Assertions.assertEquals(responseSearchVisitors.getVisitors().get(1).getClientName(), "name");
        Assertions.assertEquals(responseSearchVisitors.getVisitors().get(1).getSurname(), "surname2");
    }

    @Test
    public void searchVisitorsOrderingPaging() {
        AddVisitorRequest addVisitorRequest = new AddVisitorRequest("name", "surname1", 252525L);
        getAddVisitorService().execute(addVisitorRequest);
        AddVisitorRequest addVisitorRequest1 = new AddVisitorRequest("name", "surname2", 252525L);
        getAddVisitorService().execute(addVisitorRequest1);
        Ordering ordering = new Ordering("surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering, paging);
        ResponseSearchVisitors responseSearchVisitors = getSearchVisitorService().execute(request);

        Assertions.assertEquals(responseSearchVisitors.getVisitors().size(), 1);
        Assertions.assertEquals(responseSearchVisitors.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(responseSearchVisitors.getVisitors().get(0).getSurname(), "surname1");
    }

    private ServiceAddAllVisitors getAddVisitorService() {
        return appContext.getBean(ServiceAddAllVisitors.class);
    }

    private ServiceSearchVisitors getSearchVisitorService() {
        return appContext.getBean(ServiceSearchVisitors.class);
    }
}
