package lv.javaguru.java2.jg_entertainment.restaurant.integrationtest;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantCoreConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantCoreConfiguration.class})
public class SpringContextTest {

    @Autowired private ApplicationContext applicationContext;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Test
    public void start() {
        assertNotNull(applicationContext);
    }
}
