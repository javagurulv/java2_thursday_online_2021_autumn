package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest3 {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldDeleteCorrectDoctor() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname1", "Speciality1");
        AddDoctorResponse response1 = getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name2", "Surname2", "Speciality2");
        AddDoctorResponse response2 = getAddDoctorService().execute(request2);

        Long doctorId = response1.getNewDoctor().getId();

        DeleteDoctorRequest request3 = new DeleteDoctorRequest(doctorId);
        DeleteDoctorResponse response3 = getDeleteDoctorService().execute(request3);

        assertTrue(response3.isDoctorDeleted());

        ShowAllDoctorsResponse response4 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response4.getDoctors().size(), 1);
        assertEquals(response4.getDoctors().get(0).getName(), "Name2");
        assertEquals(response4.getDoctors().get(0).getSurname(), "Surname2");
        assertEquals(response4.getDoctors().get(0).getSpeciality(), "Speciality2");
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

}
