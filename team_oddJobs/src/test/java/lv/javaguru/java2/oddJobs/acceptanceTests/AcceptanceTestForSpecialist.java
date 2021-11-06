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
import lv.javaguru.java2.oddJobs.database.ClientRepository;
import lv.javaguru.java2.oddJobs.database.SpecialistRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Sql({"/CreateTable.sql"})
public class AcceptanceTestForSpecialist {
   @Autowired
   private AddSpecialistService addSpecialistService;
    @Autowired private  GetAllSpecialistsService getAllSpecialistsService;
    @Autowired private RemoveSpecialistService removeSpecialistService;
    @Autowired private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
//        appContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        databaseCleaner.clean();
//     jdbcTemplate.update("RUNSCRIPT FROM 'classpath:CreateTable.sql'");
//     jdbcTemplate.update("RUNSCRIPT FROM 'classpath:insertData.sql'");
//
   }


    @Test
    public void shouldReturnCorrectSpecialistList() {
        AddSpecialistRequest addSpecialistRequest1 = new AddSpecialistRequest("Name", "Surname", "Profession");
        addSpecialistService.execute(addSpecialistRequest1);

        AddSpecialistRequest addSpecialistRequest2 = new AddSpecialistRequest("Name1", "Surname1", "Profession1");
        addSpecialistService.execute(addSpecialistRequest2);

        GetAllSpecialistsResponse response = getAllSpecialistsService.execute(new GetAllSpecialistRequest());
        assertEquals(response.getSpecialists().size(), 2);
    }

//    @Test
//
//    public void shouldRemoveSpecialistFromList() {
//        AddSpecialistRequest addSpecialistRequest1 = new AddSpecialistRequest("Name", "Surname", "Profession");
//        getAllSpecialistsService.execute(addSpecialistRequest1);
//
//        AddSpecialistRequest addSpecialistRequest2 = new AddSpecialistRequest("Name1", "Surname1", "Profession1");
//        addSpecialistService.execute(addSpecialistRequest2);
//
//        RemoveSpecialistResponse response = getAllSpecialistsService.execute(new RemoveSpecialistRequest(2L, "Name1", "Surname1"));
//        assertTrue(response.isSpecialistRemoved());
//
//    }

}
