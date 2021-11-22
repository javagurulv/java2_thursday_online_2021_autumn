package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.ShowAllUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.ShowAllUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.AddAllUsersService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.ShowListUsersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantListConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest1 {

    @Autowired private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        getDatabase().cleaner();
    }

    @Test
    public void returnUserList() {
        AddUserRequest request = new AddUserRequest("name", "surname", "3271");
        getAddService().execute(request);
        AddUserRequest request1 = new AddUserRequest("name1", "surname", "3271");
        getAddService().execute(request1);
        AddUserRequest request2 = new AddUserRequest("name2", "surname", "3271");
        getAddService().execute(request2);
        AddUserRequest request3 = new AddUserRequest("name3", "surname", "3271");
        getAddService().execute(request3);

        ShowAllUsersResponse response0 = getAllUser().execute(new ShowAllUsersRequest());
        assertEquals(response0.getNewUser().size(), 4);
        assertEquals(response0.getNewUser().get(0).getUserName(), "name");
        assertEquals(response0.getNewUser().get(0).getSurname(), "surname");
        assertEquals(response0.getNewUser().get(0).getTelephoneNumber(), "3271");
        assertEquals(response0.getNewUser().get(1).getUserName(), "name1");
        assertEquals(response0.getNewUser().get(1).getSurname(), "surname");
        assertEquals(response0.getNewUser().get(1).getTelephoneNumber(), "3271");
        assertEquals(response0.getNewUser().get(2).getUserName(), "name2");
        assertEquals(response0.getNewUser().get(2).getSurname(), "surname");
        assertEquals(response0.getNewUser().get(2).getTelephoneNumber(), "3271");
        assertEquals(response0.getNewUser().get(3).getUserName(), "name3");
        assertEquals(response0.getNewUser().get(3).getSurname(), "surname");
        assertEquals(response0.getNewUser().get(3).getTelephoneNumber(), "3271");
    }

    private DatabaseCleaner getDatabase() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    private AddAllUsersService getAddService() {
        return applicationContext.getBean(AddAllUsersService.class);
    }

    private ShowListUsersService getAllUser() {
        return applicationContext.getBean(ShowListUsersService.class);
    }
}