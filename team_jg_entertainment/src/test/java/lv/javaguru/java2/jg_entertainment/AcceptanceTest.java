package lv.javaguru.java2.jg_entertainment;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestShowAllVisitorsInListRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceAddAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTest {

    private ApplicationContextRestaurant appContext = new ApplicationContextRestaurant();

    @Test
    public void shouldReturnCorrectVisitorList() {
        RequestAddVisitor addVisitor = new RequestAddVisitor("nameZ", "surnameZ", 2569874L);
        getAddVisitorService().execute(addVisitor);

        RequestAddVisitor addVisitor1 = new RequestAddVisitor("nameZ", "surnameZ", 2569874L);
        getAddVisitorService().execute(addVisitor1);

        ResponseShowAllVisitors response = getAllVisitorService().execute(new RequestShowAllVisitorsInListRestaurant());
        assertEquals(response.getNewVisitor().size(), 2);
    }

    @Test
    public void shouldDeleteVisitorFromList() {
        RequestAddVisitor addVisitor = new RequestAddVisitor("nameZ", "surnameZ", 1L);
        getAddVisitorService().execute(addVisitor);

        RequestAddVisitor addVisitor1 = new RequestAddVisitor("nameX", "surnameX", 2L);
        getAddVisitorService().execute(addVisitor1);

        ResponseDeleteVisitors response = getDeleteVisitorService().execute(new RequestDeleteVisitor(2L, "nameX"));
        assertTrue(response.ifIdVisitorDelete());
    }

    private ServiceAddAllVisitors getAddVisitorService() {
        return appContext.getBean(ServiceAddAllVisitors.class);
    }

    private ServiceShowListVisitors getAllVisitorService() {
        return appContext.getBean(ServiceShowListVisitors.class);
    }

    private ServiceDeleteVisitors getDeleteVisitorService() {
        return appContext.getBean(ServiceDeleteVisitors.class);
    }
}