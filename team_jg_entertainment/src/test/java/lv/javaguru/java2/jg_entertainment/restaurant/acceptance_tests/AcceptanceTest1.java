package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestShowAllVisitorsInListRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceAddAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.ApplicationContext;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIApplicationContextBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AcceptanceTest1 {

    private ApplicationContext appContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.jg_entertainment.restaurant");

    @Test
    public void shouldReturnCorrectListVisitor() {
        RequestAddVisitor addVisitor = new RequestAddVisitor("name", "surname", 252525L);
        getAddVisitorService().execute(addVisitor);

        RequestAddVisitor addVisitor1 = new RequestAddVisitor("name", "surname", 252525L);
        getAddVisitorService().execute(addVisitor1);

        ResponseShowAllVisitors response = getAllVisitorsService().execute(new RequestShowAllVisitorsInListRestaurant());
        Assertions.assertEquals(response.getNewVisitor().size(), 2);
    }

    private ServiceAddAllVisitors getAddVisitorService() {
        return appContext.getBean(ServiceAddAllVisitors.class);
    }

    private ServiceShowListVisitors getAllVisitorsService() {
        return appContext.getBean(ServiceShowListVisitors.class);
    }
}
