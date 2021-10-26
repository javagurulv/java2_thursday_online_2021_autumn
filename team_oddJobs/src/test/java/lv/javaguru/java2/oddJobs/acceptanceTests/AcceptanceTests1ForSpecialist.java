package lv.javaguru.java2.oddJobs.acceptanceTests;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.responce.find.FindSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.find.FindSpecialistService;
import org.junit.Before;
import lv.javaguru.java2.oddJobs.config.ApplicationConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
@Ignore
public class AcceptanceTests1ForSpecialist {

    private ApplicationContext applicationContext;

    @Before

    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    }

    @Test
    public void FindSpecialist() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession");
        getAddSpecialistService().execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name1", "Surname1", "Profession1");
        getAddSpecialistService().execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name2", "Surname", "Profession2");
        getAddSpecialistService().execute(request2);

        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null);
        FindSpecialistResponse response = getFindSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 2);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
        assertEquals(response.getSpecialists().get(1).getSpecialistName(), "Name2");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(1).getSpecialistProfession(), "Profession2");
    }

    @Test
    public void FindSpecialistOrderingAscending() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession");
        getAddSpecialistService().execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name2", "Surname", "Profession");
        getAddSpecialistService().execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name1", "Surname", "Profession");
        getAddSpecialistService().execute(request2);

        Ordering ordering = new Ordering("Name", "ASCENDING");
        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null, ordering);
        FindSpecialistResponse response = getFindSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 3);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
        assertEquals(response.getSpecialists().get(1).getSpecialistName(), "Name1");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(1).getSpecialistProfession(), "Profession");
        assertEquals(response.getSpecialists().get(2).getSpecialistName(), "Name2");
        assertEquals(response.getSpecialists().get(2).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(2).getSpecialistProfession(), "Profession");
    }

    @Test
    public void FindSpecialistOrderingDescending() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession");
        getAddSpecialistService().execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name2", "Surname", "Profession");
        getAddSpecialistService().execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name1", "Surname", "Profession");
        getAddSpecialistService().execute(request2);

        Ordering ordering = new Ordering("Name", "ASCENDING");
        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null, ordering);
        FindSpecialistResponse response = getFindSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 3);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
        assertEquals(response.getSpecialists().get(1).getSpecialistName(), "Name1");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(1).getSpecialistProfession(), "Profession");
        assertEquals(response.getSpecialists().get(2).getSpecialistName(), "Name2");
        assertEquals(response.getSpecialists().get(2).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(2).getSpecialistProfession(), "Profession");
    }


    @Test
    public void FindSpecialistPaging() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession");
        getAddSpecialistService().execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name2", "Surname", "Profession");
        getAddSpecialistService().execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name1", "Surname", "Profession");
        getAddSpecialistService().execute(request2);

        Ordering ordering = new Ordering("Name", "DESCENDING");
        Paging paging = new Paging(1, 1);
        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null, ordering, paging);
        FindSpecialistResponse response = getFindSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name2");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");

    }


    private AddSpecialistService getAddSpecialistService() {
        return applicationContext.getBean(AddSpecialistService.class);
    }

    private FindSpecialistService getFindSpecialistService() {
        return applicationContext.getBean(FindSpecialistService.class);
    }
}
