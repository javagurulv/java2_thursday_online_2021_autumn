package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.EditDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest4 {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldEditDoctorName() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
		AddDoctorResponse response1 = getAddDoctorService().execute(request1);

		Long doctorId = response1.getNewDoctor().getId();

        EditDoctorRequest request2 = new EditDoctorRequest(doctorId, 1, "Name1");
        EditDoctorResponse response2 = getEditDoctorService().execute(request2);

        assertTrue(response2.isDoctorEdited());

        ShowAllDoctorsResponse response3 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response3.getDoctors().size(), 1);
        assertEquals(response3.getDoctors().get(0).getName(), "Name1");
        assertEquals(response3.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response3.getDoctors().get(0).getSpeciality(), "Speciality1");
    }

    @Test
    public void shouldEditDoctorSurname() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname", "Speciality1");
        AddDoctorResponse response1 = getAddDoctorService().execute(request1);

		Long doctorId = response1.getNewDoctor().getId();

		EditDoctorRequest request2 = new EditDoctorRequest(doctorId, 2, "Surname1");
        EditDoctorResponse response2 = getEditDoctorService().execute(request2);

        assertTrue(response2.isDoctorEdited());

        ShowAllDoctorsResponse response3 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response3.getDoctors().size(), 1);
        assertEquals(response3.getDoctors().get(0).getName(), "Name1");
        assertEquals(response3.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response3.getDoctors().get(0).getSpeciality(), "Speciality1");
    }

   @Test
    public void shouldEditDoctorSpeciality() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname1", "Speciality");
        AddDoctorResponse response1 = getAddDoctorService().execute(request1);

	   Long doctorId = response1.getNewDoctor().getId();

	   EditDoctorRequest request2 = new EditDoctorRequest(doctorId, 3, "Speciality1");
        EditDoctorResponse response2 = getEditDoctorService().execute(request2);

       assertTrue(response2.isDoctorEdited());

        ShowAllDoctorsResponse response3 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response3.getDoctors().size(), 1);
        assertEquals(response3.getDoctors().get(0).getName(), "Name1");
        assertEquals(response3.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response3.getDoctors().get(0).getSpeciality(), "Speciality1");
    }


    private AddDoctorService getAddDoctorService() {
        return appContext.getBean(AddDoctorService.class);
    }

    private ShowAllDoctorsService getShowAllDoctorsService() {
        return appContext.getBean(ShowAllDoctorsService.class);
    }

    private EditDoctorService getEditDoctorService() {
        return appContext.getBean(EditDoctorService.class);
    }

}
