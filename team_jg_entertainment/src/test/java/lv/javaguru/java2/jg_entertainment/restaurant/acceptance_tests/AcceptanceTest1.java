package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.ShowAllUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.ShowAllUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.AddAllUsersService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.ShowListUsersService;
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
    @Autowired private AddAllUsersService addService;
    @Autowired private ShowListUsersService getUser;

    @Before
    public void setUp() {
        database.cleaner();
    }

    @Test
    @Ignore
    public void returnUserList() {
        AddUserRequest request = new AddUserRequest("name", "surname", "3271");
        AddUserRequest request1 = new AddUserRequest("name1", "surname1", "3722");
        AddUserRequest request2 = new AddUserRequest("name2", "surname2", "3723");
        addService.execute(request);
        addService.execute(request1);
        addService.execute(request2);
        ShowAllUsersResponse response0 = getUser.execute(new ShowAllUsersRequest());
        assertEquals(response0.getNewUser().size(), 3);
        assertEquals(response0.getNewUser().get(0).getUserName(), "name");
        assertEquals(response0.getNewUser().get(0).getSurname(), "surname");
        assertEquals(response0.getNewUser().get(0).getTelephoneNumber(), "3271");
        assertEquals(response0.getNewUser().get(1).getUserName(), "name1");
        assertEquals(response0.getNewUser().get(1).getSurname(), "surname1");
        assertEquals(response0.getNewUser().get(1).getTelephoneNumber(), "3722");
        assertEquals(response0.getNewUser().get(2).getUserName(), "name2");
        assertEquals(response0.getNewUser().get(2).getSurname(), "surname2");
        assertEquals(response0.getNewUser().get(2).getTelephoneNumber(), "3723");
    }
}