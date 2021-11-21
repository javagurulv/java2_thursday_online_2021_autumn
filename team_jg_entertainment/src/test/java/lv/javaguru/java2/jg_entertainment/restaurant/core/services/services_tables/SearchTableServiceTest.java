package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.SearchTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.SearchRequestTableValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchTableServiceTest {

    @Mock private TableRepository database;
    @Mock private SearchRequestTableValidator validator;
    @InjectMocks private SearchTableService service;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnErrorsEmptyFiled(){
        SearchTableRequest request = new SearchTableRequest(null,null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("title", "must not be empty"));
        errors.add(new CoreError("id", "must not be empty"));
        Mockito.when(validator.validator(request)).thenReturn(errors);
        SearchTableResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(), 2);
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnSearchByTitle(){
        SearchTableRequest request = new SearchTableRequest("title",null);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Table> tables = new ArrayList<>();
        tables.add(new Table("title",2,10.00));
        Mockito.when(database.findByTitleTable("title")).thenReturn(tables);
        SearchTableResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getTables().size(),1);
        assertEquals(response.getTables().get(0).getTitle(), "title");
    }

    @Test
    public void shouldReturnSearchTitleOrderingAscending(){
        OrderingTable orderingTable = new OrderingTable("title", "ASCENDING");
        SearchTableRequest request = new SearchTableRequest("title",null, orderingTable);
        Mockito.when(database.findByTitleTable("title")).thenReturn(new ArrayList<>());
        List<Table> tables = new ArrayList<>();
        tables.add(new Table("title",2,10.00));
        tables.add(new Table("title",1000,1000.00));
        Mockito.when(database.findByTitleTable("title")).thenReturn(tables);
        SearchTableResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getTables().size(),2);
        assertEquals(response.getTables().get(0).getTitle(), "title");
        assertEquals(response.getTables().get(0).getTitle(), "title");
    }

    @Test
    public void shouldReturnSearchPaging(){
        OrderingTable orderingTable = new OrderingTable("title", "ASCENDING");
        PagingTable pagingTable = new PagingTable(1,1);
        SearchTableRequest request = new SearchTableRequest("title",null, orderingTable, pagingTable);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Table> tables = new ArrayList<>();
        tables.add(new Table("title",2,10.00));
        tables.add(new Table("title2",1000,1000.00));
        Mockito.when(database.findByTitleTable("title")).thenReturn(tables);
        SearchTableResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getTables().size(),1);
        assertEquals(response.getTables().get(0).getTitle(), "title");
    }
}