package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.UsersRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.ShowAllUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.ShowAllUsersResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowListUserServiceTest {

    @Mock private UsersRepository usersRepository;
    @InjectMocks private ShowListUsersService service;

    @Test
    public void shouldGetVisitorsFromDb() {
        List<User> users = new ArrayList<>();
        users.add(new User("name", "surname"));
        Mockito.when(usersRepository.showAllUsersInList()).thenReturn(users);
        ShowAllUsersRequest request = new ShowAllUsersRequest();
        ShowAllUsersResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getNewUser().size(), 1);
        assertEquals(response.getNewUser().get(0).getUserName(), "name");
        assertEquals(response.getNewUser().get(0).getSurname(), "surname");
    }
}