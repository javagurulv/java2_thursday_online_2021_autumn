package lv.javaguru.java2.services.Get;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Get.GetAllSpecialistRequest;
import lv.javaguru.java2.core.responce.Get.GetAllSpecialistsResponse;
import lv.javaguru.java2.database.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetAllSpecialistsServiceTest {

    @Mock
    private Database database;
    @InjectMocks
    private GetAllSpecialistsService service;

    @Test

    public void shouldGetSpecialistsFromDb() {
        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname", "Profession"));
        Mockito.when(database.getAllSpecialist()).thenReturn(specialists);

        GetAllSpecialistRequest request = new GetAllSpecialistRequest();
        GetAllSpecialistsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
    }

}