package lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests;

import lv.javaguru.java2.jg_entertainment.restaurant.acceptance_tests.databaseCleaner.DatabaseCleaner;
import lv.javaguru.java2.jg_entertainment.restaurant.configuration.RestaurantCoreConfiguration;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.SearchTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.AddTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.GetAllTablesService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.RemoveTableService;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables.SearchTableService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestaurantCoreConfiguration.class})
@Sql({"/Schema.sql"})
public class TableAcceptanceTest2 {

    @Autowired private ApplicationContext appContext;

    @BeforeEach
    public void setup(){
        getDatabaseCleaner().cleaner();
    }

    @Test
    public void searchTable() {
        AddTableRequest request = new AddTableRequest("title", 1, 10);
        getAddTableService().execute(request);
        AddTableRequest request1 = new AddTableRequest("title", 2, 20);
        getAddTableService().execute(request1);
        SearchTableRequest request2 = new SearchTableRequest("title", null);
        SearchTableResponse response = getSearchTableService().execute(request2);
        assertEquals(response.getTables().size(),2);
        assertEquals(response.getTables().get(0).getTitle(), "title");
        assertEquals(response.getTables().get(0).getTableCapacity(), 1);
        assertEquals(response.getTables().get(0).getPrice(), 10,00);
        assertEquals(response.getTables().get(1).getTitle(), "title");
        assertEquals(response.getTables().get(1).getTableCapacity(), 2);
        assertEquals(response.getTables().get(1).getPrice(), 20,00);
    }

    @Test
    public void shouldDeleteTable() {
        AddTableRequest request = new AddTableRequest("title", 1, 10);
        AddTableResponse response = getAddTableService().execute(request);
        response.getNewTable().getId();

        AddTableRequest request1 = new AddTableRequest("title2", 1, 10);
        AddTableResponse response1 = getAddTableService().execute(request1);
        response1.getNewTable().getId();
        Long idTable2 = response1.getNewTable().getId();
        RemoveTableRequest deleteRequest = new RemoveTableRequest(idTable2);
        RemoveTableResponse deleteResponse = deleteTableService().execute(deleteRequest);
        assertTrue(deleteResponse.isTableRemoved());

        GetAllTablesResponse getAllTablesResponse = showListTableService().execute(new GetAllTablesRequest());
        assertEquals(getAllTablesResponse.getTables().size(), 1);
        assertEquals(getAllTablesResponse.getTables().get(0).getTitle(), "title");
        assertEquals(getAllTablesResponse.getTables().get(0).getTableCapacity(), 1);
        assertEquals(getAllTablesResponse.getTables().get(0).getPrice(), 10,00);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

    private AddTableService getAddTableService() {
        return appContext.getBean(AddTableService.class);
    }

    private SearchTableService getSearchTableService() {
        return appContext.getBean(SearchTableService.class);
    }

    private RemoveTableService deleteTableService() {
        return appContext.getBean(RemoveTableService.class);
    }

   private GetAllTablesService showListTableService(){
        return appContext.getBean(GetAllTablesService.class);
   }

}
