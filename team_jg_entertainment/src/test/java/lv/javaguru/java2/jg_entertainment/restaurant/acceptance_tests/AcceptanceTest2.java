package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.SearchVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.AddAllVisitorsService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.SearchVisitorsService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AcceptanceTest2 {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);
        getDatabaseCleaner().cleaner();
    }

    @Test
    @Ignore
    public void searchVisitor() {
        AddVisitorRequest request1 = new AddVisitorRequest("name", "surname1", "252525");
        getAddVisitorService().execute(request1);
        AddVisitorRequest request2 = new AddVisitorRequest("name", "surname2", "252525");
        getAddVisitorService().execute(request2);

        SearchVisitorsRequest request3 = new SearchVisitorsRequest("name", null);
        SearchVisitorsResponse response = getSearchVisitorService().execute(request3);

        Assertions.assertEquals(response.getVisitors().size(), 2);
        Assertions.assertEquals(response.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(0).getSurname(), "surname1");
        Assertions.assertEquals(response.getVisitors().get(1).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(1).getSurname(), "surname2");
    }

    @Test
    @Ignore
    public void searchVisitorsOrderingDescending() {
        AddVisitorRequest request1 = new AddVisitorRequest("name", "surname1", "252525");
        getAddVisitorService().execute(request1);
        AddVisitorRequest request2 = new AddVisitorRequest("name", "surname2", "252525");
        getAddVisitorService().execute(request2);

        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        SearchVisitorsResponse response = getSearchVisitorService().execute(request);

        Assertions.assertEquals(response.getVisitors().size(), 2);
        Assertions.assertEquals(response.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(0).getSurname(), "surname2");
        Assertions.assertEquals(response.getVisitors().get(1).getClientName(), "name");
        Assertions.assertEquals(response.getVisitors().get(1).getSurname(), "surname1");
    }

    @Test
    @Ignore
    public void searchVisitorsOrderingAscending() {
        AddVisitorRequest addVisitorRequest = new AddVisitorRequest("name", "surname1", "252525");
        getAddVisitorService().execute(addVisitorRequest);
        AddVisitorRequest addVisitorRequest1 = new AddVisitorRequest("name", "surname2", "252525");
        getAddVisitorService().execute(addVisitorRequest1);

        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        SearchVisitorsResponse searchVisitorsResponse = getSearchVisitorService().execute(request);

        Assertions.assertEquals(searchVisitorsResponse.getVisitors().size(), 2);
        Assertions.assertEquals(searchVisitorsResponse.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname1");
        Assertions.assertEquals(searchVisitorsResponse.getVisitors().get(1).getClientName(), "name");
        Assertions.assertEquals(searchVisitorsResponse.getVisitors().get(1).getSurname(), "surname2");
    }

    @Test
    @Ignore
    public void searchVisitorsOrderingPaging() {
        AddVisitorRequest addVisitorRequest = new AddVisitorRequest("name", "surname1", "252525");
        getAddVisitorService().execute(addVisitorRequest);
        AddVisitorRequest addVisitorRequest1 = new AddVisitorRequest("name", "surname2", "252525");
        getAddVisitorService().execute(addVisitorRequest1);
        Ordering ordering = new Ordering("surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering, paging);
        SearchVisitorsResponse searchVisitorsResponse = getSearchVisitorService().execute(request);

        Assertions.assertEquals(searchVisitorsResponse.getVisitors().size(), 1);
        Assertions.assertEquals(searchVisitorsResponse.getVisitors().get(0).getClientName(), "name");
        Assertions.assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname1");
    }

    private AddAllVisitorsService getAddVisitorService() {
        return appContext.getBean(AddAllVisitorsService.class);
    }

    private SearchVisitorsService getSearchVisitorService() {
        return appContext.getBean(SearchVisitorsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}
