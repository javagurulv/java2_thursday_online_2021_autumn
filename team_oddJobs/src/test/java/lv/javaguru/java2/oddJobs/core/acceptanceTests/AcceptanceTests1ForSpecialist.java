package lv.javaguru.java2.oddJobs.core.acceptanceTests;

import lv.javaguru.java2.oddJobs.core.DatabaseCleaner;
import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.response.find.FindSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.find.FindSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllSpecialistsService;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveSpecialistService;
import org.junit.Before;
import lv.javaguru.java2.oddJobs.config.SpringCoreConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
@Sql({"/CreateTable.sql"})
public class AcceptanceTests1ForSpecialist {

    @Autowired
    private AddSpecialistService addSpecialistService;
    @Autowired
    private GetAllSpecialistsService getAllSpecialistsService;
    @Autowired
    private RemoveSpecialistService removeSpecialistService;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private FindSpecialistService findSpecialistService;

    @Before

    public void setup() {
        databaseCleaner.clean();

    }

    @Test
    public void FindSpecialist() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession","personalCode","city");
        addSpecialistService.execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name1", "Surname1", "Profession1","personalCode1","city1");
        addSpecialistService.execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name2", "Surname", "Profession2","personalCode2","city2");
        addSpecialistService.execute(request2);

        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null);
        FindSpecialistResponse response = findSpecialistService.execute(request3);

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
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession","personalCode","city");
        addSpecialistService.execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name2", "Surname", "Profession","personalCode1","city");
        addSpecialistService.execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name1", "Surname", "Profession","personalCode2","city");
        addSpecialistService.execute(request2);

        Ordering ordering = new Ordering("Name", "ASCENDING");
        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null, ordering);
        FindSpecialistResponse response = findSpecialistService.execute(request3);

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
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession","personalCode","city");
        addSpecialistService.execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name2", "Surname", "Profession","personalCode1","city");
        addSpecialistService.execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name1", "Surname", "Profession","personalCode2","city");
        addSpecialistService.execute(request2);

        Ordering ordering = new Ordering("Name", "ASCENDING");
        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null, ordering);
        FindSpecialistResponse response = findSpecialistService.execute(request3);

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
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession","personalCode","city");
        addSpecialistService.execute(request);
        AddSpecialistRequest request1 = new AddSpecialistRequest("Name2", "Surname", "Profession","personalCode1","city");
        addSpecialistService.execute(request1);
        AddSpecialistRequest request2 = new AddSpecialistRequest("Name1", "Surname", "Profession","personalCode2","city");
        addSpecialistService.execute(request2);

        Ordering ordering = new Ordering("Name", "DESCENDING");
        Paging paging = new Paging(1, 1);
        FindSpecialistRequest request3 = new FindSpecialistRequest(null, "Surname", null, ordering, paging);
        FindSpecialistResponse response = findSpecialistService.execute(request3);

        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name2");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");

    }
}
