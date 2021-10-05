package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;

@DIComponent
public class ShowAllDoctorsUIAction implements DoctorUIActions {

    @DIDependency private ShowAllDoctorsService showAllDoctors;

    @Override
    public void execute() {
        System.out.println("Doctor list: ");
        ShowAllDoctorsRequest request = new ShowAllDoctorsRequest();
        ShowAllDoctorsResponse response = showAllDoctors.execute(request);
        System.out.println(response.getDoctors());
        System.out.println("Doctor list end.");
    }
}
