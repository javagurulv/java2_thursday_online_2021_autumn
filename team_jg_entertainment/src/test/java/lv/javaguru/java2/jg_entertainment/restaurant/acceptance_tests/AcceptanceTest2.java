package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantListConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.SearchUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.AddAllUsersService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users.SearchUsersService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantListConfiguration.class})
@Sql({"/create.sql"})
public class AcceptanceTest2 {

    @Autowired private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(RestaurantListConfiguration.class);
        getDatabaseCleaner().cleaner();
    }

    @Test
    @Ignore
    public void searchVisitor() {
        AddUserRequest request1 = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(request1);
        AddUserRequest request2 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(request2);

        SearchUsersRequest request3 = new SearchUsersRequest("name", null);
        SearchUsersResponse response = getSearchVisitorService().execute(request3);

        Assertions.assertEquals(response.getUsers().size(), 2);
        Assertions.assertEquals(response.getUsers().get(0).getUserName(), "name");
        Assertions.assertEquals(response.getUsers().get(0).getSurname(), "surname1");
        Assertions.assertEquals(response.getUsers().get(1).getUserName(), "name");
        Assertions.assertEquals(response.getUsers().get(1).getSurname(), "surname2");
    }

    @Test
    @Ignore
    public void searchVisitorsOrderingDescending() {
        AddUserRequest request1 = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(request1);
        AddUserRequest request2 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(request2);

        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
        SearchUsersResponse response = getSearchVisitorService().execute(request);

        Assertions.assertEquals(response.getUsers().size(), 2);
        Assertions.assertEquals(response.getUsers().get(0).getUserName(), "name");
        Assertions.assertEquals(response.getUsers().get(0).getSurname(), "surname2");
        Assertions.assertEquals(response.getUsers().get(1).getUserName(), "name");
        Assertions.assertEquals(response.getUsers().get(1).getSurname(), "surname1");
    }

    @Test
    @Ignore
    public void searchVisitorsOrderingAscending() {
        AddUserRequest addUserRequest = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(addUserRequest);
        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(addUserRequest1);

        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
        SearchUsersResponse searchUsersResponse = getSearchVisitorService().execute(request);

        Assertions.assertEquals(searchUsersResponse.getUsers().size(), 2);
        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
        Assertions.assertEquals(searchUsersResponse.getUsers().get(1).getUserName(), "name");
        Assertions.assertEquals(searchUsersResponse.getUsers().get(1).getSurname(), "surname2");
    }

    @Test
    @Ignore
    public void searchVisitorsOrderingPaging() {
        AddUserRequest addUserRequest = new AddUserRequest("name", "surname1", "252525");
        getAddVisitorService().execute(addUserRequest);
        AddUserRequest addUserRequest1 = new AddUserRequest("name", "surname2", "252525");
        getAddVisitorService().execute(addUserRequest1);
        Ordering ordering = new Ordering("surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering, paging);
        SearchUsersResponse searchUsersResponse = getSearchVisitorService().execute(request);

        Assertions.assertEquals(searchUsersResponse.getUsers().size(), 1);
        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        Assertions.assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
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
