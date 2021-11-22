package lv.javaguru.java2.oddJobs.core.services.find;


import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.find.FindClientsResponse;
import lv.javaguru.java2.oddJobs.core.validations.find.FindClientsValidator;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.domain.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private FindClientsValidator validator;
    @InjectMocks
    private FindClientsService service;

    List<CoreError> errors;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);

        errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        errors.add(new CoreError("Surname", "Must not be empty!"));
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {

        //given
        FindClientsRequest request = new FindClientsRequest(null, null);
        when(validator.validate(request)).thenReturn(errors);

        //when
        FindClientsResponse response = service.execute(request);

        //then
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
        assertEquals(response.getErrors().get(1).getField(), "Surname");
        assertEquals(response.getErrors().get(1).getMessage(), "Must not be empty!");
        verify(validator).validate(request);
        verifyNoInteractions(clientRepository);

    }

    @Test
    public void shouldReturnClientWhenSearchedByName() {

        //given
        FindClientsRequest request = new FindClientsRequest("Name", null);
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Name", "Surname","personalCode","city"));
        when(clientRepository.findClientsByName("Name")).thenReturn(clients);


        //when
        FindClientsResponse response = service.execute(request);

        //then
        assertFalse(response.hasErrors());
        assertEquals(response.getClients().get(0).getClientName(), "Name");
        assertEquals(response.getClients().get(0).getClientSurname(), "Surname");

    }    @Test
    public void shouldReturnClientWhenSearchedBySurname() {

        //given
        FindClientsRequest request = new FindClientsRequest(null, "Surname");
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Name", "Surname","personalCode","city"));
        when(clientRepository.findClientBySurname("Surname")).thenReturn(clients);


        //when
        FindClientsResponse response = service.execute(request);

        //then
        assertFalse(response.hasErrors());
        assertEquals(response.getClients().size(), 1);
        assertEquals(response.getClients().get(0).getClientName(), "Name");
        assertEquals(response.getClients().get(0).getClientSurname(), "Surname");

    }


}
