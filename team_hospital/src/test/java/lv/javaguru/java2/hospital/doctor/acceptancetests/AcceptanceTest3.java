package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.DoctorApplicationContext;
import lv.javaguru.java2.hospital.doctor.core.requests.*;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.DeleteDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest3 {

   /* private DoctorApplicationContext appContest = new DoctorApplicationContext();

    @Test
    public void shouldDeleteCorrectDoctor() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name2", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        DeleteDoctorRequest request3 = new DeleteDoctorRequest("1");
        DeleteDoctorResponse response1 = getDeleteDoctorService().execute(request3);

        assertEquals(response1.isDoctorDeleted(), true);

        ShowAllDoctorsResponse response2 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response2.getDoctors().size(), 1);
        assertEquals(response2.getDoctors().get(0).getName(), "Name2");
        assertEquals(response2.getDoctors().get(0).getSurname(), "Surname2");
        assertEquals(response2.getDoctors().get(0).getSpeciality(), "Speciality2");
    }


    private AddDoctorService getAddDoctorService() {
        return appContest.getBean(AddDoctorService.class);
    }

    private ShowAllDoctorsService getShowAllDoctorsService() {
        return appContest.getBean(ShowAllDoctorsService.class);
    }

    private DeleteDoctorService getDeleteDoctorService() {
        return appContest.getBean(DeleteDoctorService.class);
    }*/
}
