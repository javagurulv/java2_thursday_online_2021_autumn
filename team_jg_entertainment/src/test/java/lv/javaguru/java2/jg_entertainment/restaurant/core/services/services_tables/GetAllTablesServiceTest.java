package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.table_repository.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.GetAllTablesRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.GetAllTablesResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllTablesServiceTest {

    @Mock private TableRepository database;
    @InjectMocks private GetAllTablesService service;

    @Test
    public void shouldGetTables(){
        List<Table> tableList = new ArrayList<>();
        tableList.add(new Table("title", 1, 10.00));
        Mockito.when(database.getAllTables()).thenReturn(tableList);
        GetAllTablesRequest request = new GetAllTablesRequest();
        GetAllTablesResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getTables().size(), 1);
        assertEquals(response.getTables().get(0).getTitle(), "title");
        assertEquals(response.getTables().get(0).getTableCapacity(), 1);
        assertEquals(response.getTables().get(0).getPrice(), 10.00);
    }
}