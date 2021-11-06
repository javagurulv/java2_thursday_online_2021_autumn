package lv.javaguru.java2.oddJobs.acceptanceTests;

import lv.javaguru.java2.oddJobs.DatabaseCleaner;
import lv.javaguru.java2.oddJobs.config.ApplicationConfiguration;
import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllClientsRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllClientsResponse;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveClientResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddClientService;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllClientsService;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveClientService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Sql({"/CreateTable.sql"})
public class AcceptanceTestForClient {

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddClientService addClientService;
    @Autowired
    private RemoveClientService removeClientService;
    @Autowired
    private GetAllClientsService getAllClientsService;


    @Before
    public void setup() {
        databaseCleaner.clean();

    }

    @Test
    public void shouldReturnClientsList() {
        //given
        AddClientRequest addClientRequest0 = new AddClientRequest("Name0", "Surname0");
        AddClientRequest addClientRequest1 = new AddClientRequest("Name1", "Surname1");

        //when
        addClientService.execute(addClientRequest0);
       addClientService.execute(addClientRequest1);

        //then
        GetAllClientsResponse getAllClientsResponse = getAllClientsService.execute(new GetAllClientsRequest());
        assertEquals(getAllClientsResponse.getClients().size(), 2);
        assertEquals(getAllClientsResponse.getClients().get(0).getClientName(), "Name0");
        assertEquals(getAllClientsResponse.getClients().get(0).getClientSurname(), "Surname0");
        assertEquals(getAllClientsResponse.getClients().get(1).getClientName(), "Name1");
        assertEquals(getAllClientsResponse.getClients().get(1).getClientSurname(), "Surname1");
    }

    @Test
    public void shouldRemoveClientsFromList() {
        AddClientRequest addClientRequest0 = new AddClientRequest("Name0", "Surname0");
        AddClientRequest addClientRequest1 = new AddClientRequest("Name1", "Surname1");
        addClientService.execute(addClientRequest0);
        addClientService.execute(addClientRequest1);

        //when
        RemoveClientResponse response = removeClientService.execute(new RemoveClientRequest("Name0", "Surname0", 1L));

        //then
        assertTrue(response.isClientRemoved());

    }


}
