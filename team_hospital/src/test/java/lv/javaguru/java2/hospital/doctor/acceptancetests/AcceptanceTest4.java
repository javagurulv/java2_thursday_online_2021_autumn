package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.DoctorApplicationContext;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.EditDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest4 {
/*
    private DoctorApplicationContext appContest = new DoctorApplicationContext();

    @Test
    public void shouldEditDoctorName() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        EditDoctorRequest request2 = new EditDoctorRequest("1", 1, "Name1");
        EditDoctorResponse response1 = getEditDoctorService().execute(request2);

        assertEquals(response1.isDoctorEdited(), true);

        ShowAllDoctorsResponse response2 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response2.getDoctors().size(), 1);
        assertEquals(response2.getDoctors().get(0).getName(), "Name1");
        assertEquals(response2.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response2.getDoctors().get(0).getSpeciality(), "Speciality1");
    }

    /*@Test
    public void shouldEditDoctorSurname() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname", "Speciality1");
        getAddDoctorService().execute(request1);

        EditDoctorRequest request2 = new EditDoctorRequest("1", 2, "Surname1");
        EditDoctorResponse response1 = getEditDoctorService().execute(request2);

        assertEquals(response1.isDoctorEdited(), true);

        ShowAllDoctorsResponse response2 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response2.getDoctors().size(), 1);
        assertEquals(response2.getDoctors().get(0).getName(), "Name1");
        assertEquals(response2.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response2.getDoctors().get(0).getSpeciality(), "Speciality1");
    }*/

   /* @Test
    public void shouldEditDoctorSpeciality() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname1", "Speciality");
        getAddDoctorService().execute(request1);

        EditDoctorRequest request2 = new EditDoctorRequest("1", 3, "Speciality1");
        EditDoctorResponse response1 = getEditDoctorService().execute(request2);

        assertEquals(response1.isDoctorEdited(), true);

        ShowAllDoctorsResponse response2 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response2.getDoctors().size(), 1);
        assertEquals(response2.getDoctors().get(0).getName(), "Name1");
        assertEquals(response2.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response2.getDoctors().get(0).getSpeciality(), "Speciality1");
    }


    private AddDoctorService getAddDoctorService() {
        return appContest.getBean(AddDoctorService.class);
    }

    private ShowAllDoctorsService getShowAllDoctorsService() {
        return appContest.getBean(ShowAllDoctorsService.class);
    }

    private EditDoctorService getEditDoctorService() {
        return appContest.getBean(EditDoctorService.class);
    }
    */
}
