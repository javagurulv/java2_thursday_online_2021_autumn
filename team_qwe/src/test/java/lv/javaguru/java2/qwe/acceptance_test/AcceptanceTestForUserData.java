package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.domain.*;
import lv.javaguru.java2.qwe.core.requests.user_requests.*;
import lv.javaguru.java2.qwe.core.responses.user_responses.*;
import lv.javaguru.java2.qwe.core.services.user_services.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class AcceptanceTestForUserData {

    @Autowired private ApplicationContext appContext;
    @Autowired private JdbcTemplate jdbcTemplate;

    //;INIT=RUNSCRIPT FROM 'classpath:schema.sql'\\;RUNSCRIPT FROM 'classpath:data.sql';


    @Before
    public void init() {
        jdbcTemplate.update("RUNSCRIPT FROM 'classpath:schema.sql'");
        jdbcTemplate.update("RUNSCRIPT FROM 'classpath:data.sql'");



/*        jdbcTemplate.update("DROP TABLE IF EXISTS stocks, bonds, users, users_positions CASCADE");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `stocks` (\n" +
                "  `ticker` VARCHAR(10) NOT NULL,\n" +
                "  `name` VARCHAR(100) NOT NULL,\n" +
                "  `industry` VARCHAR(50) NOT NULL,\n" +
                "  `currency` CHAR(3) NOT NULL,\n" +
                "  `market_price` DECIMAL(8,2) NOT NULL,\n" +
                "  `dividend_yield` DECIMAL(4,2) NOT NULL,\n" +
                "  `risk_weight` DECIMAL(5,4) NOT NULL,\n" +
                "  PRIMARY KEY (`ticker`)\n" +
                ")");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `bonds` (\n" +
                "  `ticker` VARCHAR(10) NOT NULL,\n" +
                "  `name` VARCHAR(100) NOT NULL,\n" +
                "  `industry` VARCHAR(50) NOT NULL,\n" +
                "  `currency` CHAR(3) NOT NULL,\n" +
                "  `market_price` DECIMAL(8,2) NOT NULL,\n" +
                "  `coupon` DECIMAL(4,2) NOT NULL,\n" +
                "  `rating` CHAR(4),\n" +
                "  `nominal` DECIMAL(10,2) NOT NULL,\n" +
                "  `maturity` DATE NOT NULL,\n" +
                "  PRIMARY KEY (`ticker`)\n" +
                ")");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `users` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(100) NOT NULL,\n" +
                "  `age` INTEGER NOT NULL,\n" +
                "  `type` VARCHAR(50) NOT NULL,\n" +
                "  `initial_investment` DECIMAL(11,2),\n" +
                "  `cash` DECIMAL(11,2),\n" +
                "  `portfolio_generation_date` DATE,\n" +
                "  `risk_tolerance` INTEGER,\n" +
                "  PRIMARY KEY(`id`)\n" +
                ")");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `users_positions` (\n" +
                "  `user_id` BIGINT NOT NULL,\n" +
                "  `security_ticker` VARCHAR(10) NOT NULL,\n" +
                "  `amount` INTEGER NOT NULL,\n" +
                "  `purchase_price` DECIMAL(8,2) NOT NULL,\n" +
                "  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),\n" +
                "  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)\n" +
                ")");
        jdbcTemplate.update("INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES\n" +
                "      ('Alexander', 25, 'SUPER_RICH', 1000000.00, 1000000.00, NULL, 5),\n" +
                "      ('Tatyana', 32, 'UPPER_MIDDLE', 125000.00, 125000.00, NULL, 4),\n" +
                "      ('Vladimir', 78, 'LOWER_MIDDLE', 30000.00, 30000.00, NULL, 1),\n" +
                "      ('John', 55, 'MIDDLE', 50000.00, 50000.00, NULL, 3);");*/
    }


    @Test
    public void addUserToDatabaseTest1() {
//        System.out.println("START");
//        getAllUserListService().getUserData().getAllUserList().forEach(System.out::println);
//        System.out.println("END");
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