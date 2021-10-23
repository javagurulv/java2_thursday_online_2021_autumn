package lv.javaguru.java2.oddJobs.acceptanceTests;

import lv.javaguru.java2.oddJobs.DatabaseCleaner;
import lv.javaguru.java2.oddJobs.config.ApplicationConfiguration;
import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllClientsRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllClientsResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddClientService;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllClientsService;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveClientService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

@Ignore
public class AcceptanceTestForClient {

    ApplicationContext appContext;


    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        getDatabaseCleaner().clean();

    }

    @Test
    public void shouldReturnClientsList() {
        //given
        AddClientRequest addClientRequest0 = new AddClientRequest("Name0", "Surname0");
        AddClientRequest addClientRequest1 = new AddClientRequest("Name1", "Surname1");

        //when
        getAddClientService().execute(addClientRequest0);
        getAddClientService().execute(addClientRequest1);

        //then
        GetAllClientsResponse getAllClientsResponse = getAllClientsService().execute(new GetAllClientsRequest());
        assertEquals(getAllClientsResponse.getClients().size(), 2);
        assertEquals(getAllClientsResponse.getClients().get(0).getClientName(), "Name0");
        assertEquals(getAllClientsResponse.getClients().get(0).getClientSurname(), "Surname0");
        assertEquals(getAllClientsResponse.getClients().get(1).getClientName(), "Name1");
        assertEquals(getAllClientsResponse.getClients().get(1).getClientSurname(), "Surname1");
    }

//    @Test
//    public void shouldRemoveClientsFromList() {
//
//        //given
//        getAddClientService().execute(addClientRequest0);
//        RemoveClientRequest request0 = new RemoveClientRequest("Name0", "Surname0",1L);
//
//        //when
//        RemoveClientResponse response = getRemoveClientService().execute(request0);
//
//        //then
//        assertTrue(response.isClientRemoved());
//
//    }

    private AddClientService getAddClientService() {
        return appContext.getBean(AddClientService.class);
    }

    private RemoveClientService getRemoveClientService() {
        return appContext.getBean(RemoveClientService.class);
    }

    private GetAllClientsService getAllClientsService() {
        return appContext.getBean(GetAllClientsService.class);
    }

    private GetAllClientsResponse getAllClientsResponse() {
        return appContext.getBean(GetAllClientsResponse.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }


}
