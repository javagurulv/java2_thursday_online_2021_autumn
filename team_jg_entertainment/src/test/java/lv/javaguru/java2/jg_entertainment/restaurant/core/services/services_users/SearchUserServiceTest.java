package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.SearchUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users.SearchUsersRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchUserServiceTest {

    @Mock private UsersRepository database;
    @Mock private SearchUsersRequestValidator validator;
    @InjectMocks private SearchUsersService serviceSearch;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(serviceSearch, "orderingEnabled", true);
        ReflectionTestUtils.setField(serviceSearch, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchUsersRequest request = new SearchUsersRequest(null, null);
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(new CoreError("name", "can not be empty!"));
        errorList.add(new CoreError("surname", "can not be empty!"));
        Mockito.when(validator.validator(request)).thenReturn(errorList);
        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertTrue(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getErrorsList().size(), 2);
        Mockito.verify(validator).validator(request);
        Mockito.verify(validator).validator(any());
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldSearchByName() {
        SearchUsersRequest request = new SearchUsersRequest("name", null);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname"));
        Mockito.when(database.findByNameUser("name")).thenReturn(users);

        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertFalse(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getUsers().size(), 1);
        assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchBySurname() {
        SearchUsersRequest request = new SearchUsersRequest(null, "surname");
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname"));
        Mockito.when(database.findBySurnameUser("surname")).thenReturn(users);
        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertFalse(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getUsers().size(), 1);
        assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchByNameAndSurname() {
        SearchUsersRequest request = new SearchUsersRequest("name", "surname");
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname"));
        Mockito.when(database.findByNameAndSurname("name", "surname")).thenReturn(users);
        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertFalse(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getUsers().size(), 1);
        assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchByNameWithOrderingAscending() {
        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname1"));
        users.add(new User("name", "surname2"));
        Mockito.when(database.findByNameUser("name")).thenReturn(users);

        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertFalse(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getUsers().size(), 2);
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
        assertEquals(searchUsersResponse.getUsers().get(1).getSurname(), "surname2");
    }

    @Test
    public void shouldSearchByNameWithOrderingDescending() {
        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchUsersRequest request = new SearchUsersRequest("name", null, ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname1"));
        users.add(new User("name", "surname2"));
        Mockito.when(database.findByNameUser("name")).thenReturn(users);

        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertFalse(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getUsers().size(), 2);
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname2");
        assertEquals(searchUsersResponse.getUsers().get(1).getSurname(), "surname1");
    }

    @Test
    public void shouldSearchByNameWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchUsersRequest request = new SearchUsersRequest("name", null, null, paging);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname1"));
        users.add(new User("name", "surname2"));
        Mockito.when(database.findByNameUser("name")).thenReturn(users);

        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertFalse(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getUsers().size(), 1);
        assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname1");
    }

    @Test
    public void shouldSearchByNameWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchUsersRequest request = new SearchUsersRequest("name", null, null, paging);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname1"));
        users.add(new User("name", "surname2"));
        Mockito.when(database.findByNameUser("name")).thenReturn(users);

        SearchUsersResponse searchUsersResponse = serviceSearch.execute(request);
        assertFalse(searchUsersResponse.hasError());
        assertEquals(searchUsersResponse.getUsers().size(), 1);
        assertEquals(searchUsersResponse.getUsers().get(0).getUserName(), "name");
        assertEquals(searchUsersResponse.getUsers().get(0).getSurname(), "surname2");
    }
}