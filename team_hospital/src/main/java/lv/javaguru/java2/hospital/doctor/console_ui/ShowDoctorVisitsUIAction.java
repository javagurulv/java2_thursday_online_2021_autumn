package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.ShowDoctorVisitsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowDoctorVisitsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.ShowDoctorVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowDoctorVisitsUIAction implements DoctorUIAction{

    @Autowired private ShowDoctorVisitsService showDoctorVisits;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        Long id = getUserInput.getUserLongInput("Please, enter the doctor's id: ");
        ShowDoctorVisitsRequest request = new ShowDoctorVisitsRequest(id);
        ShowDoctorVisitsResponse response = showDoctorVisits.execute(request);
        System.out.println("Doctor's all visits list: ");
        System.out.println(response.getVisits());
        System.out.println("Doctor's all visits list end.");
    }
}
