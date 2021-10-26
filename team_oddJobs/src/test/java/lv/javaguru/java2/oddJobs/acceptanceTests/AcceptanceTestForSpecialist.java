package lv.javaguru.java2.oddJobs.acceptanceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lv.javaguru.java2.oddJobs.DatabaseCleaner;
import lv.javaguru.java2.oddJobs.config.ApplicationConfiguration;
import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.get.GetAllSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllSpecialistsResponse;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.get.GetAllSpecialistsService;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveSpecialistService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Ignore
public class AcceptanceTestForSpecialist {
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        getDatabaseCleaner().clean();
    }


    @Test
    public void shouldReturnCorrectSpecialistList() {
        AddSpecialistRequest addSpecialistRequest1 = new AddSpecialistRequest("Name", "Surname", "Profession");
        getAddSpecialistService().execute(addSpecialistRequest1);

        AddSpecialistRequest addSpecialistRequest2 = new AddSpecialistRequest("Name1", "Surname1", "Profession1");
        getAddSpecialistService().execute(addSpecialistRequest2);

        GetAllSpecialistsResponse response = getAllSpecialistsService().execute(new GetAllSpecialistRequest());
        assertEquals(response.getSpecialists().size(), 2);
    }

    @Test

    public void shouldRemoveSpecialistFromList() {
        AddSpecialistRequest addSpecialistRequest1 = new AddSpecialistRequest("Name", "Surname", "Profession");
        getAddSpecialistService().execute(addSpecialistRequest1);

        AddSpecialistRequest addSpecialistRequest2 = new AddSpecialistRequest("Name1", "Surname1", "Profession1");
        getAddSpecialistService().execute(addSpecialistRequest2);

        RemoveSpecialistResponse response = gerRemoveSpecialistService().execute(new RemoveSpecialistRequest(2L, "Name1", "Surname1"));
        assertTrue(response.isSpecialistRemoved());

    }


    private AddSpecialistService getAddSpecialistService() {
        return appContext.getBean(AddSpecialistService.class);
    }

    private GetAllSpecialistsService getAllSpecialistsService() {
        return appContext.getBean(GetAllSpecialistsService.class);

    }

    private RemoveSpecialistService gerRemoveSpecialistService() {
        return appContext.getBean(RemoveSpecialistService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
