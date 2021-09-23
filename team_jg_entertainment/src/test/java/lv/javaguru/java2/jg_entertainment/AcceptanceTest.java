package lv.javaguru.java2.jg_entertainment;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestShowAllVisitorsInListRestaurant;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseShowAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceAddAllVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ServiceShowListVisitors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTest {

    private ApplicationContext appContext = new ApplicationContext();

    @Test
    public void shouldReturnCorrectVisitorList() {
        RequestAddVisitor addVisitor = new RequestAddVisitor("nameZ", "surnameZ", 2569874L);
        getAddVisitorService().execute(addVisitor);

        RequestAddVisitor addVisitor1 = new RequestAddVisitor("nameZ", "surnameZ",2569874L);
        getAddVisitorService().execute(addVisitor1);

        ResponseShowAllVisitors response = getAllVisitorService().execute(new RequestShowAllVisitorsInListRestaurant());
        assertEquals(response.getNewVisitor().size(), 2);
    }

    private ServiceAddAllVisitors getAddVisitorService(){
        return appContext.getBean(ServiceAddAllVisitors.class);
    }

    private ServiceShowListVisitors getAllVisitorService(){
        return appContext.getBean(ServiceShowListVisitors.class);
    }
}