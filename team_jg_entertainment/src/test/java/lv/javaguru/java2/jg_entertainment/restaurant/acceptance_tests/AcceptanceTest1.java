package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ShowAllVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.AddAllVisitorsService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ShowListVisitorsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantListConfiguration.class})
@Sql({"/schemaLGRestaurant.sql"})
public class AcceptanceTest1 {

    @Autowired private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);
        getDatabaseCleaner().cleaner();
    }

    @Test
    @Ignore
    public void shouldReturnCorrectListVisitor() {
        AddVisitorRequest addVisitor = new AddVisitorRequest("name", "surname", "252525");
        getAddVisitorService().execute(addVisitor);

        AddVisitorRequest addVisitor1 = new AddVisitorRequest("name", "surname", "252525");
        getAddVisitorService().execute(addVisitor1);

        ShowAllVisitorsResponse response = getAllVisitorsService().execute(new ShowAllVisitorsRequest());
        Assertions.assertEquals(response.getNewVisitor().size(), 2);
    }

    private AddAllVisitorsService getAddVisitorService() {
        return appContext.getBean(AddAllVisitorsService.class);
    }

    private ShowListVisitorsService getAllVisitorsService() {
        return appContext.getBean(ShowListVisitorsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
