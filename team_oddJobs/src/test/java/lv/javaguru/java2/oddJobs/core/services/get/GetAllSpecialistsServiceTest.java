package lv.javaguru.java2.oddJobs.core.services.get;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.get.GetAllSpecialistsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetAllSpecialistsServiceTest {

    @Mock
    private SpecialistRepository specialistRepository;
    @InjectMocks
    private GetAllSpecialistsService service;

    @Test

    public void shouldGetSpecialistsFromDb() {
        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.getAllSpecialist()).thenReturn(specialists);

        GetAllSpecialistRequest request = new GetAllSpecialistRequest();
        GetAllSpecialistsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
    }

}