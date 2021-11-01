package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.DatabaseCleaner;
import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import lv.javaguru.java2.hospital.doctor.core.requests.*;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.DeleteDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest3 {

    private ApplicationContext appContext;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Ignore
    public void shouldDeleteCorrectDoctor() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname1", "Speciality1");
        AddDoctorResponse response1 = getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name2", "Surname2", "Speciality2");
        AddDoctorResponse response2 = getAddDoctorService().execute(request2);

        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, request1.getName(), request1.getSurname(), request1.getSpeciality());
        SearchDoctorsResponse response3 = getSearchDoctorService().execute(request3);
        Long doctorId = response3.getDoctors().get(0).getId();

        DeleteDoctorRequest request4 = new DeleteDoctorRequest(doctorId);
        DeleteDoctorResponse response4 = getDeleteDoctorService().execute(request4);

        assertTrue(response4.isDoctorDeleted());

        ShowAllDoctorsResponse response5 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response5.getDoctors().size(), 1);
        assertEquals(response5.getDoctors().get(0).getName(), "Name2");
        assertEquals(response5.getDoctors().get(0).getSurname(), "Surname2");
        assertEquals(response5.getDoctors().get(0).getSpeciality(), "Speciality2");
    }


    private AddDoctorService getAddDoctorService() {
        return appContext.getBean(AddDoctorService.class);
    }

    private ShowAllDoctorsService getShowAllDoctorsService() {
        return appContext.getBean(ShowAllDoctorsService.class);
    }

    private DeleteDoctorService getDeleteDoctorService() {
        return appContext.getBean(DeleteDoctorService.class);
    }

    private SearchDoctorsService getSearchDoctorService() {
        return appContext.getBean(SearchDoctorsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
