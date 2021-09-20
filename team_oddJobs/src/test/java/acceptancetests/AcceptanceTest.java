package acceptancetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lv.javaguru.java2.ApplicationContext;
import lv.javaguru.java2.core.requests.Add.AddSpecialistRequest;
import lv.javaguru.java2.core.requests.Get.GetAllSpecialistRequest;
import lv.javaguru.java2.core.requests.Remove.RemoveSpecialistRequest;
import lv.javaguru.java2.core.responce.Get.GetAllSpecialistsResponse;
import lv.javaguru.java2.core.responce.Remove.RemoveSpecialistResponse;
import lv.javaguru.java2.services.Add.AddSpecialistService;
import lv.javaguru.java2.services.Get.GetAllSpecialistsService;
import lv.javaguru.java2.services.Remove.RemoveSpecialistService;
import org.junit.Test;

public class AcceptanceTest {
    ApplicationContext applicationContext = new ApplicationContext();


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
        return applicationContext.getBean(AddSpecialistService.class);
    }

    private GetAllSpecialistsService getAllSpecialistsService() {
        return applicationContext.getBean(GetAllSpecialistsService.class);

    }

    private RemoveSpecialistService gerRemoveSpecialistService() {
        return applicationContext.getBean(RemoveSpecialistService.class);
    }
}
