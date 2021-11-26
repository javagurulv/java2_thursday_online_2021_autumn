package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.config.SpringCoreConfiguration;
import lv.javaguru.java2.qwe.core.domain.*;
import lv.javaguru.java2.qwe.core.requests.user_requests.*;
import lv.javaguru.java2.qwe.core.responses.user_responses.*;
import lv.javaguru.java2.qwe.core.services.user_services.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
@Sql({"/schema.sql"})
@Sql({"/data.sql"})
public class AcceptanceTestForUserData {

    @Autowired private ApplicationContext appContext;

    @Test
    public void addUserToDatabaseTest1() {
        AddUserRequest request1 = new AddUserRequest(
                "Marina", "42", "WEALTHY", "500000"
        );
        getAddUserService().execute(request1);
        GetAllUserListRequest request2 = new GetAllUserListRequest();
        GetAllUserListResponse response = getAllUserListService().execute(request2);
        assertEquals(5, response.getList().size());
    }

    @Test
    public void addUserToDatabaseTest2() {
        AddUserRequest request = new AddUserRequest(
                "Michael", "12", "LOWER_MIDDLE", "25000" //ошибка!
        );
        getAddUserService().execute(request);
        GetAllUserListRequest request2 = new GetAllUserListRequest();
        GetAllUserListResponse response = getAllUserListService().execute(request2);
        assertEquals(4, response.getList().size());
    }

    @Test
    public void addUserToDatabaseTest3() {
        AddUserRequest request1 = new AddUserRequest(
                "Marina", "42", "WEALTHY", "500000"
        );
        AddUserRequest request2 = new AddUserRequest(
                "Michael", "36", "SUPER_RICH", "2500000"
        );
        AddUserResponse response1 = getAddUserService().execute(request1);
        AddUserResponse response2 = getAddUserService().execute(request2);
        assertEquals(5, response1.getNewUser().getId());
        assertEquals(6, response2.getNewUser().getId());
    }

    @Test
    public void removeUserFromDatabaseTest1() {
        RemoveUserRequest request1 = new RemoveUserRequest("3");
        GetAllUserListRequest request2 = new GetAllUserListRequest();
        RemoveUserResponse response1 = getRemoveUserService().execute(request1);
        GetAllUserListResponse response2 = getAllUserListService().execute(request2);
        assertTrue(response1.isRemoved());
        assertEquals(3, response2.getList().size());
    }

    @Test
    public void removeUserFromDatabaseTest2() {
        RemoveUserRequest request1 = new RemoveUserRequest("Alexander");
        GetAllUserListRequest request2 = new GetAllUserListRequest();
        RemoveUserResponse response1 = getRemoveUserService().execute(request1);
        GetAllUserListResponse response2 = getAllUserListService().execute(request2);
        assertTrue(response1.isRemoved());
        assertEquals(3, response2.getList().size());
    }

    @Test
    public void removeUserFromDatabaseTest3() {
        RemoveUserRequest request1 = new RemoveUserRequest("AlexOnder"); //ошибка!
        GetAllUserListRequest request2 = new GetAllUserListRequest();
        RemoveUserResponse response1 = getRemoveUserService().execute(request1);
        GetAllUserListResponse response2 = getAllUserListService().execute(request2);
        assertFalse(response1.isRemoved());
        assertEquals(4, response2.getList().size());
    }

    @Test
    public void findUserByNameTest1() {
        FindUserByNameRequest request = new FindUserByNameRequest("Vladimir");
        FindUserByNameResponse response = getFindUserByName().execute(request);
        User user = new User("Vladimir", 78, Type.LOWER_MIDDLE, 30000);
        user.setId(3);
        assertEquals(user, response.getUser());
    }

    @Test
    public void findUserByNameTest2() {
        FindUserByNameRequest request = new FindUserByNameRequest("2");
        FindUserByNameResponse response = getFindUserByName().execute(request);
        User user = new User("Tatyana", 32, Type.UPPER_MIDDLE, 125000);
        user.setId(2);
        assertEquals(user, response.getUser());
    }

    @Test
    public void findUserByNameTest3() {
        FindUserByNameRequest request = new FindUserByNameRequest("Alex"); //ошибка!
        FindUserByNameResponse response = getFindUserByName().execute(request);
        assertNull(response.getUser());
    }


    private AddUserService getAddUserService() {
        return appContext.getBean(AddUserService.class);
    }

    private RemoveUserService getRemoveUserService() {
        return appContext.getBean(RemoveUserService.class);
    }

    private GetAllUserListService getAllUserListService() {
        return appContext.getBean(GetAllUserListService.class);
    }

    private FindUserByNameService getFindUserByName() {
        return appContext.getBean(FindUserByNameService.class);
    }

}