package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.core.domain.Type;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetAllUserListResponse;
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
public class GetAllUserListServiceTest {

    @Mock private UserData userData;
    @InjectMocks private GetAllUserListService service;

    @Test
    public void shouldGetAllUsersFromUserData() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"Alexander", 25, Type.SUPER_RICH, 1_000_000));
        userList.add(new User(2,"Marina", 42, Type.WEALTHY, 500_000));
        userList.add(new User(3,"Vladimir", 65, Type.LOWER_MIDDLE, 30_000));

        Mockito.when(userData.getAllUserList()).thenReturn(userList);

        GetAllUserListRequest request = new GetAllUserListRequest();
        GetAllUserListResponse response = service.execute(request);
        assertEquals(response.getList().size(), 3);
        assertEquals(userList, response.getList());
    }

}