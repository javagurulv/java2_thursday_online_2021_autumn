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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantListConfiguration.class})
@Sql({"/create.sql"})
public class AcceptanceTest1 {

    @Autowired private DatabaseCleaner database;
    @Autowired private AddAllVisitorsService addService;
    @Autowired private ShowListVisitorsService getUser;

    @Before
    public void setUp() {
        database.cleaner();
    }

    @Test
    @Ignore
    public void returnUserList() {
        AddVisitorRequest request = new AddVisitorRequest("name", "surname", "3271");
        AddVisitorRequest request1 = new AddVisitorRequest("name1", "surname1", "3722");
        AddVisitorRequest request2 = new AddVisitorRequest("name2", "surname2", "3723");
        addService.execute(request);
        addService.execute(request1);
        addService.execute(request2);
        ShowAllVisitorsResponse response0 = getUser.execute(new ShowAllVisitorsRequest());
        assertEquals(response0.getNewVisitor().size(), 3);
        assertEquals(response0.getNewVisitor().get(0).getClientName(), "name");
        assertEquals(response0.getNewVisitor().get(0).getSurname(), "surname");
        assertEquals(response0.getNewVisitor().get(0).getTelephoneNumber(), "3271");
        assertEquals(response0.getNewVisitor().get(1).getClientName(), "name1");
        assertEquals(response0.getNewVisitor().get(1).getSurname(), "surname1");
        assertEquals(response0.getNewVisitor().get(1).getTelephoneNumber(), "3722");
        assertEquals(response0.getNewVisitor().get(2).getClientName(), "name2");
        assertEquals(response0.getNewVisitor().get(2).getSurname(), "surname2");
        assertEquals(response0.getNewVisitor().get(2).getTelephoneNumber(), "3723");
    }
}