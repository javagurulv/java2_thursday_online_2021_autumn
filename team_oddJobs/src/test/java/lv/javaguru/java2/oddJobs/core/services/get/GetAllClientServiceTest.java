package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllClientsRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllClientsResponse;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    public GetAllClientsService getAllClientsService;

    @Test

    public void shouldGetAllClientsFromDatabase() {

        //given
        List<Client> clientsList = new ArrayList<>();
        clientsList.add(new Client("Name","Surname","personalCode","city"));
        clientsList.add(new Client("Name1","Surname1","personalCode","city"));
        when(clientRepository.getAllClients()).thenReturn(clientsList);

        GetAllClientsRequest request = new GetAllClientsRequest();

        //when

        GetAllClientsResponse response = getAllClientsService.execute(request);

        //then
        assertFalse(response.hasErrors());
        assertEquals(response.getClients().size(),2);
        assertEquals(response.getClients().get(0).getClientName(),"Name");
        assertEquals(response.getClients().get(0).getClientSurname(),"Surname");
        assertEquals(response.getClients().get(1).getClientName(),"Name1");
        assertEquals(response.getClients().get(1).getClientSurname(),"Surname1");

    }

}
