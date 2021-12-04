package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests.databaseCleaner.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantCoreConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.SearchUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.AddAllUsersService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.SearchUsersService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantCoreConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest2 {

    @Autowired private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        getDatabaseCleaner().cleaner();
    }

    @Test
    public void searchVisitor() {
        AddUserRequest request1 = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(request1);
        AddUserRequest request2 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(request2);
        SearchUsersRequest request3 = new SearchUsersRequest("name", null);
        SearchUsersResponse response = getSearchVisitorService().execute(request3);
        assertEquals(response.getUsers().size(), 2);
        assertEquals(response.getUsers().get(0).getUserName(), "name");
        assertEquals(response.getUsers().get(0).getSurname(), "surname1");
        assertEquals(response.getUsers().get(1).getUserName(), "name");
        assertEquals(response.getUsers().get(1).getSurname(), "surname2");
    }

    @Test
    public void searchVisitorsOrderingDescending() {
        AddUserRequest request1 = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(request1);
        AddUserRequest request2 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(request2);
        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
        SearchUsersResponse response = getSearchVisitorService().execute(request);
        assertEquals(response.getUsers().size(), 2);
        assertEquals(response.getUsers().get(0).getUserName(), "name");
        assertEquals(response.getUsers().get(0).getSurname(), "surname2");
        assertEquals(response.getUsers().get(1).getUserName(), "name");
        assertEquals(response.getUsers().get(1).getSurname(), "surname1");
    }

    @Test
    public void searchVisitorsOrderingAscending() {
        AddUserRequest addUserRequest = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(addUserRequest);
        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(addUserRequest1);
        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
        SearchUsersResponse searchUsersResponse = getSearchVisitorService().execute(request);
        assertEquals(searchUsersResponse.getUsers().size(), 2);
        assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
        assertEquals(searchUsersResponse.getUsers().get(1).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(1).getSurname(), "surname2");
    }

    @Test
    public void searchVisitorsOrderingPaging() {
        AddUserRequest addUserRequest = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(addUserRequest);
        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(addUserRequest1);
        Ordering ordering = new Ordering("surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering, paging);
        SearchUsersResponse searchUsersResponse = getSearchVisitorService().execute(request);
        assertEquals(searchUsersResponse.getUsers().size(), 1);
        assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
    }

    private AddAllUsersService getAddVisitorService() {
        return appContext.getBean(AddAllUsersService.class);
    }

    private SearchUsersService getSearchVisitorService() {
        return appContext.getBean(SearchUsersService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}
