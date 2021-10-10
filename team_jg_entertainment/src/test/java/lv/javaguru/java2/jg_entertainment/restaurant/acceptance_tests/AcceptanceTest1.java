package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ShowAllVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.AddAllVisitorsService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ShowListVisitorsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AcceptanceTest1 {

    private ApplicationContext appContext =
            new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);

    @Test
    public void shouldReturnCorrectListVisitor() {
        AddVisitorRequest addVisitor = new AddVisitorRequest("name", "surname", 252525L);
        getAddVisitorService().execute(addVisitor);

        AddVisitorRequest addVisitor1 = new AddVisitorRequest("name", "surname", 252525L);
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
}
