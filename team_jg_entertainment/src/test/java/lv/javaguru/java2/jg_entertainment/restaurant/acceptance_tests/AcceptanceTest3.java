package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.DeleteVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.ShowAllVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.AddVisitorResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.DeleteVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ShowAllVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.AddAllVisitorsService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.DeleteVisitorsService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors.ShowListVisitorsService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest3 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);
        databaseCleaner().cleaner();
    }

    @Test
    @Ignore
    public void shouldDeleteUser() {
        AddVisitorRequest addVisitor1 = new AddVisitorRequest("name", "surname", "252525");
        AddVisitorResponse addVisitorResponse1 = addAllVisitorsService().execute(addVisitor1);
        Long idUser1 = addVisitorResponse1.getNewVisitor().getIdClient();

        AddVisitorRequest addVisitor2 = new AddVisitorRequest("name", "surname2", "252525");
        AddVisitorResponse response = addAllVisitorsService().execute(addVisitor2);
        Long idUser2 = response.getNewVisitor().getIdClient();

        DeleteVisitorRequest deleteVisitorRequest = new DeleteVisitorRequest(idUser2, "name");
        DeleteVisitorsResponse deleteVisitorsResponse = deleteVisitorsService().execute(deleteVisitorRequest);

        assertTrue(deleteVisitorsResponse.ifIdVisitorDelete());

        ShowAllVisitorsResponse showUserResponse = showListVisitorsService().execute(new ShowAllVisitorsRequest());

        assertEquals(showUserResponse.getNewVisitor().size(), 1);
        assertEquals(showUserResponse.getNewVisitor().get(0).getClientName(), "name");
        assertEquals(showUserResponse.getNewVisitor().get(0).getSurname(), "surname");
        assertEquals(showUserResponse.getNewVisitor().get(0).getTelephoneNumber(), "252525");
    }


    private AddAllVisitorsService addAllVisitorsService() {
        return applicationContext.getBean(AddAllVisitorsService.class);
    }

    private DeleteVisitorsService deleteVisitorsService() {
        return applicationContext.getBean(DeleteVisitorsService.class);
    }

    private ShowListVisitorsService showListVisitorsService() {
        return applicationContext.getBean(ShowListVisitorsService.class);
    }

    private DatabaseCleaner databaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }
}
