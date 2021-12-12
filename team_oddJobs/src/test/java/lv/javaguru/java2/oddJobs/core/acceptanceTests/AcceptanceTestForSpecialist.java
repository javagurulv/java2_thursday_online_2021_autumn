package lv.javaguru.java2.oddJobs.core.acceptanceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lv.javaguru.java2.oddJobs.core.DatabaseCleaner;
import lv.javaguru.java2.oddJobs.config.SpringCoreConfiguration;
import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.get.GetAllSpecialistsResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllSpecialistsService;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveSpecialistService;
import lv.javaguru.java2.oddJobs.core.validations.add.AddSpecialistValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
@Sql({"/CreateTable.sql"})
public class AcceptanceTestForSpecialist {
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddSpecialistService addSpecialistService;
    @Autowired
    private GetAllSpecialistsService getAllSpecialistsService;
    @Autowired
    private RemoveSpecialistService removeSpecialistService;
    @Autowired
    private AddSpecialistValidator validator;



    @Before
    public void setup() {
        databaseCleaner.clean();
    }


    @Test
    public void shouldReturnCorrectSpecialistList() {
        AddSpecialistRequest addSpecialistRequest1 = new AddSpecialistRequest("Name", "Surname", "Profession","personalCode","city");
        addSpecialistService.execute(addSpecialistRequest1);

        AddSpecialistRequest addSpecialistRequest2 = new AddSpecialistRequest("Name1", "Surname1", "Profession1","personalCode1","city1");
        addSpecialistService.execute(addSpecialistRequest2);
        GetAllSpecialistsResponse response = getAllSpecialistsService.execute(new GetAllSpecialistRequest());
        assertEquals(response.getSpecialists().size(), 2);
    }

    @Test

    public void shouldRemoveSpecialistFromList() {

        AddSpecialistRequest addSpecialistRequest1 = new AddSpecialistRequest("Name", "Surname", "Profession","personalCode","city");
        addSpecialistService.execute(addSpecialistRequest1);

        AddSpecialistRequest addSpecialistRequest2 = new AddSpecialistRequest("Name1", "Surname1", "Profession1","personalCode1","city1");
        addSpecialistService.execute(addSpecialistRequest2);

        RemoveSpecialistResponse response = removeSpecialistService.execute(new RemoveSpecialistRequest(2L, "Name1", "Surname1"));

        assertTrue(response.isSpecialistRemoved());


    }
}
