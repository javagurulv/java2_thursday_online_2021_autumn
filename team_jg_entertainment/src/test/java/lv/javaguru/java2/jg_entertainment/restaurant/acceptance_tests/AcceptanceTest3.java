package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.DeleteUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.ShowAllUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.AddUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.DeleteUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.ShowAllUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.AddAllUsersService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.DeleteUsersService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.ShowListUsersService;
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
        AddUserRequest addUser1 = new AddUserRequest("name", "surname", "252525");
        AddUsersResponse addUserResponse1 = addAllUsersService().execute(addUser1);
        Long idUser1 = addUserResponse1.getNewUser().getUserId();

        AddUserRequest addUser2 = new AddUserRequest("name", "surname2", "252525");
        AddUsersResponse response = addAllUsersService().execute(addUser2);
        Long idUser2 = response.getNewUser().getUserId();

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(idUser2, "name");
        DeleteUsersResponse deleteVisitorsResponse = deleteUsersService().execute(deleteUserRequest);

        assertTrue(deleteVisitorsResponse.ifUserIdDeleted());

        ShowAllUsersResponse showUserResponse = showListUsersService().execute(new ShowAllUsersRequest());

        assertEquals(showUserResponse.getNewUser().size(), 1);
        assertEquals(showUserResponse.getNewUser().get(0).getUserName(), "name");
        assertEquals(showUserResponse.getNewUser().get(0).getSurname(), "surname");
        assertEquals(showUserResponse.getNewUser().get(0).getTelephoneNumber(), "252525");
    }


    private AddAllUsersService addAllUsersService() {
        return applicationContext.getBean(AddAllUsersService.class);
    }

    private DeleteUsersService deleteUsersService() {
        return applicationContext.getBean(DeleteUsersService.class);
    }

    private ShowListUsersService showListUsersService() {
        return applicationContext.getBean(ShowListUsersService.class);
    }

    private DatabaseCleaner databaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }
}
