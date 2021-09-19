package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.RemoveSecurityRequest;
import lv.javaguru.java2.qwe.core.responses.data_responses.RemoveSecurityResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveSecurityServiceTest {

    @Mock
    private Database database;
    @InjectMocks
    private RemoveSecurityService service;

    @Test
    public void shouldRemoveSecurityByName() {
        Mockito.when(database.removeSecurity("Alibaba")).thenReturn(true);
        RemoveSecurityRequest request = new RemoveSecurityRequest("Alibaba");
        RemoveSecurityResponse response = service.execute(request);
        assertTrue(response.isRemoved());
    }

    @Test
    public void returnNoSecurityWithSuchName() {
        RemoveSecurityRequest request = new RemoveSecurityRequest("Intel");
        RemoveSecurityResponse response = service.execute(request);
        assertFalse(response.isRemoved());
    }

}