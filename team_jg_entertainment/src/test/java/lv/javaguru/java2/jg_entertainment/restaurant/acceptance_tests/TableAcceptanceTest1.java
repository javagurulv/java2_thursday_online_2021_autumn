package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantCoreConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantCoreConfiguration.class})
@Sql({"/Schema.sql"})
public class TableAcceptanceTest1 {

    @Autowired private ApplicationContext appContext;

    @BeforeEach
    public void setUp(){
        getDatabase().cleaner();
    }

    @Test
    public void returnTableList(){
        AddTableRequest request = new AddTableRequest("title", 1, 10.00);
        getAddService().execute(request);
        AddTableRequest request1 = new AddTableRequest("title1", 1, 10.00);
        getAddService().execute(request1);
        AddTableRequest request2 = new AddTableRequest("title2", 1, 10.00);
        getAddService().execute(request2);
        AddTableRequest request3 = new AddTableRequest("title3", 1, 10.00);
        getAddService().execute(request3);

        GetAllTablesResponse response = getAllTablesService().execute(new GetAllTablesRequest());
        assertEquals(response.getTables().size(), 4);
        assertEquals(response.getTables().get(0).getTitle(), "title");
        assertEquals(response.getTables().get(0).getTableCapacity(), 1);
        assertEquals(response.getTables().get(0).getPrice(), 10,00);
        assertEquals(response.getTables().get(1).getTitle(), "title1");
        assertEquals(response.getTables().get(1).getTableCapacity(), 1);
        assertEquals(response.getTables().get(1).getPrice(), 10,00);
        assertEquals(response.getTables().get(2).getTitle(), "title2");
        assertEquals(response.getTables().get(2).getTableCapacity(), 1);
        assertEquals(response.getTables().get(2).getPrice(), 10,00);
        assertEquals(response.getTables().get(3).getTitle(), "title3");
        assertEquals(response.getTables().get(3).getTableCapacity(), 1);
        assertEquals(response.getTables().get(3).getPrice(), 10,00);
    }

    private DatabaseCleaner getDatabase() {
        return appContext.getBean(DatabaseCleaner.class);
    }

    private AddTableService getAddService() {
        return appContext.getBean(AddTableService.class);
    }

    private GetAllTablesService getAllTablesService() {
        return appContext.getBean(GetAllTablesService.class);
    }

}
