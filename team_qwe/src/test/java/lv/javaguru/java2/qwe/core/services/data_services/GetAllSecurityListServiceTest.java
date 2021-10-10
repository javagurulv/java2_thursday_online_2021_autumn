package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
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
public class GetAllSecurityListServiceTest {

    @Mock private Database database;
    @InjectMocks private GetAllSecurityListService service;

    @Test
    public void shouldGetAllSecuritiesFromDatabase() {
        List<Security> securityList = new ArrayList<>();
        securityList.add(new Stock("Alibaba", "Technology", "USD",
                175.32, 0, 1.32));
        Mockito.when(database.getAllSecurityList()).thenReturn(securityList);

        GetAllSecurityListRequest request = new GetAllSecurityListRequest();
        GetAllSecurityListResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getList().size(), 1);
        assertEquals(response.getList().get(0), new Stock("Alibaba", "Technology", "USD",
                175.32, 0, 1.32));
    }

}