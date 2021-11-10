package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.requests.user_requests.RemoveUserRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class RemoveUserValidatorTest {

    @Autowired private ApplicationContext appContext;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        jdbcTemplate.update("RUNSCRIPT FROM 'classpath:schema.sql'");
        jdbcTemplate.update("INSERT INTO stocks VALUES('AAPL US','Apple Inc.','Technology','USD',148.19,0.59,1)");
        jdbcTemplate.update("INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES\n" +
                "  ('Alexander', 40, 4, 1000000.00, 1000000.00, NULL, 5),\n" +
                "  ('Vladimir', 78, 0, 30000, 30000, NULL, 1);");
        jdbcTemplate.update("INSERT INTO users_positions (user_id, security_ticker, amount, purchase_price) VALUES\n" +
                "  (1,'AAPL US',1000,148.19);");
    }

    @Test
    public void shouldReturnEmptyList1() {
        RemoveUserRequest request = new RemoveUserRequest("Vladimir");
        List<CoreError> errors = getValidator().validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList2() {
        RemoveUserRequest request = new RemoveUserRequest("2");
        List<CoreError> errors = getValidator().validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnError1() {
        RemoveUserRequest request = new RemoveUserRequest("");
        List<CoreError> errors = getValidator().validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "No user with such name!");
    }

    @Test
    public void shouldReturnError2() {
        RemoveUserRequest request = new RemoveUserRequest("Vlad");
        List<CoreError> errors = getValidator().validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "No user with such name!");
    }

    @Test
    public void shouldReturnError3() {
        RemoveUserRequest request = new RemoveUserRequest("1");
        List<CoreError> errors = getValidator().validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(),
                "can't remove user, because there are securities in the portfolio!");
    }

    @Test
    public void shouldReturnError4() {
        RemoveUserRequest request = new RemoveUserRequest("Alexander");
        List<CoreError> errors = getValidator().validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(),
                "can't remove user, because there are securities in the portfolio!");
    }

    private RemoveUserValidator getValidator() {
        return appContext.getBean(RemoveUserValidator.class);
    }

}