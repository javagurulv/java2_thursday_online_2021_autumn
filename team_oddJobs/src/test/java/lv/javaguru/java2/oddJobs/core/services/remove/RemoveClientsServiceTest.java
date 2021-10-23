package lv.javaguru.java2.oddJobs.core.services.remove;


import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.remove.RemoveClientValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RemoveClientsServiceTest {

    @Mock
    private Database database;
    @Mock
    RemoveClientValidator validator;
    @InjectMocks
    private RemoveClientService removeClientService;

    @Test

    public void shouldRemoveClientWhenNameIsProvided() {
        //given
        RemoveClientRequest request = new RemoveClientRequest("Name","Surname");

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name","Must not be empty!"));


        //when
        //then

    }


}
