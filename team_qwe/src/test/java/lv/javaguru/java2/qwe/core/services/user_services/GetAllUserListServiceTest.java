package lv.javaguru.java2.qwe.core.services.user_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.Type;
import lv.javaguru.java2.qwe.User;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.database.UserData;
import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetAllUserListResponse;
import lv.javaguru.java2.qwe.core.services.data_services.GetAllSecurityListService;
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

    @Mock
    private UserData userData;
    @InjectMocks
    private GetAllUserListService service;

    @Test
    public void shouldGetAllUsersFromUserData() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Alexander", 25, Type.SUPER_RICH, 1_000_000));
        userList.add(new User("Marina", 42, Type.WEALTHY, 500_000));
        userList.add(new User("Vladimir", 65, Type.LOWER_MIDDLE, 30_000));

        Mockito.when(userData.showUserList()).thenReturn(userList);

        GetAllUserListRequest request = new GetAllUserListRequest();
        GetAllUserListResponse response = service.execute(request);
        assertEquals(response.getList().size(), 3);
        assertEquals(userList, response.getList());
    }

}