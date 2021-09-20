package acceptancetests;

import lv.javaguru.java2.ApplicationContext;
import lv.javaguru.java2.core.requests.Add.AddSpecialistRequest;
import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.Find.FindSpecialistResponse;
import lv.javaguru.java2.services.Add.AddSpecialistService;
import lv.javaguru.java2.services.Find.FindSpecialistService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest1 {
    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void searchSpecialist() {
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name", "Surname1", "Profession");
        getAddSpecialistService().execute(request1);

        AddSpecialistRequest request2 = new AddSpecialistRequest("Name", "Surname2", "Profession");
        getAddSpecialistService().execute(request2);

        FindSpecialistRequest request3 = new FindSpecialistRequest("Name", null, null);
        FindSpecialistResponse response = getSearchSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 2);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname1");
        assertEquals(response.getSpecialists().get(1).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname2");
    }

    @Test
    public void searchSpecialistsOrderingDescending() {
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name", "Surname1", "Profession");
        getAddSpecialistService().execute(request1);

        AddSpecialistRequest request2 = new AddSpecialistRequest("Name", "Surname2", "Profession");
        getAddSpecialistService().execute(request2);

        Ordering ordering = new Ordering("Surname", "DESCENDING");
        FindSpecialistRequest request3 = new FindSpecialistRequest("Name", null, null, ordering);
        FindSpecialistResponse response = getSearchSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 2);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname2");
        assertEquals(response.getSpecialists().get(1).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname1");
    }

    @Test
    public void searchSpecialistsOrderingAscending() {
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name", "Surname1", "Profession");
        getAddSpecialistService().execute(request1);

        AddSpecialistRequest request2 = new AddSpecialistRequest("Name", "Surname2", "Profession");
        getAddSpecialistService().execute(request2);

        Ordering ordering = new Ordering("Surname", "ASCENDING");
        FindSpecialistRequest request3 = new FindSpecialistRequest("Name", null, null, ordering);
        FindSpecialistResponse response = getSearchSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 2);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname1");
        assertEquals(response.getSpecialists().get(1).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname2");
    }

    @Test
    public void searchSpecialistsOrderingPaging() {
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name", "Surname1", "Profession");
        getAddSpecialistService().execute(request1);

        AddSpecialistRequest request2 = new AddSpecialistRequest("Name", "Surname1", "Profession");
        getAddSpecialistService().execute(request2);

        Ordering ordering = new Ordering("Surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        FindSpecialistRequest request3 = new FindSpecialistRequest("Name", null, null, ordering, paging);
        FindSpecialistResponse response = getSearchSpecialistService().execute(request3);

        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname1");
    }

    private AddSpecialistService getAddSpecialistService() {
        return applicationContext.getBean(AddSpecialistService.class);
    }

    private FindSpecialistService getSearchSpecialistService() {
        return applicationContext.getBean(FindSpecialistService.class);
    }

}
