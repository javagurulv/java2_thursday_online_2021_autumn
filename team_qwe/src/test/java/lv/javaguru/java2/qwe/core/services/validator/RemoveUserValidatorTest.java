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
        jdbcTemplate.update("DROP TABLE IF EXISTS stocks, bonds, users, users_positions CASCADE");
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
        jdbcTemplate.update("INSERT INTO stocks VALUES('AAPL US','Apple Inc.','Technology','USD',148.19,0.59,1)");
        jdbcTemplate.update("INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES\n" +
                "  ('Alexander', 40, 'SUPER_RICH', 1000000.00, 1000000.00, NULL, 5),\n" +
                "  ('Vladimir', 78, 'LOWER_MIDDLE', 30000, 30000, NULL, 1);");
        jdbcTemplate.update("INSERT INTO users_positions VALUES\n" +
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